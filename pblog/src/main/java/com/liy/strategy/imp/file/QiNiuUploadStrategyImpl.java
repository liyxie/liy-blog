package com.liy.strategy.imp.file;

import com.liy.config.file.QiNiuYunConfig;
import com.liy.domain.entity.SystemFileConfig;
import com.liy.domain.model.file.QiNiuPutRet;
import com.liy.util.FileUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.FileInfo;
import com.liy.strategy.FileUploadStrategy;
import com.liy.util.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;


@Service("qiNiuUploadStrategyImpl")
@RequiredArgsConstructor
public class QiNiuUploadStrategyImpl implements FileUploadStrategy {

    private final Logger logger = LoggerFactory.getLogger(QiNiuUploadStrategyImpl.class);

    private final QiNiuYunConfig qiNiuYunConfig;

    public void list() {
        BucketManager.FileListIterator fileListIterator = qiNiuYunConfig.getDefaultFileListIterator();
        while (fileListIterator.hasNext()) {
            FileInfo[] next = fileListIterator.next();
            for (FileInfo fileInfo : next) {
                logger.info("文件打印开始,文件名：{}",qiNiuYunConfig.getQi_niu_url() + fileInfo.key);
                logger.info("文件类别打印开始,类别：{}",fileInfo.mimeType);
                logger.info("文件大小打印开始,大小：{}",fileInfo.fsize);
            }
        }
    }

    @Override
    public FileUploadStrategy updateConfig(SystemFileConfig systemFileConfig) {
        qiNiuYunConfig.initOrUpdate(systemFileConfig);
        return this;
    }

    @Override
    public String fileUpload(String path, MultipartFile file){
        String suffix = null;

        UploadManager uploadManager = qiNiuYunConfig.getUploadManager();
        //...生成上传凭证，然后准备上传
        String upToken = qiNiuYunConfig.getAuthToken();
        FileInputStream inputStream = null;
        String key = qiNiuYunConfig.getPath() + path + UUIDUtils.getUuid() + "." + suffix;
        try {
            suffix = FileUtils.getExtension(file.getInputStream());
            inputStream = (FileInputStream) file.getInputStream();
            Response response = uploadManager.put(inputStream, key, upToken,null,null);
            //解析上传成功的结果
            QiNiuPutRet putRet = response.jsonToObject(QiNiuPutRet.class);
            key = qiNiuYunConfig.getQi_niu_url() + "/" + putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            logger.info("QiniuException:{}",r.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return key;
    }

    /**
     * 批量删除文件
     * @return
     */
    @Override
    public Boolean deleteFile(String ...keys) {
        BucketManager bucketManager = qiNiuYunConfig.getBucketManager();
        try {
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(qiNiuYunConfig.getQi_niu_bucket(), keys);
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keys.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keys[i];
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.data.error);
                }
            }
            return true;
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
            return false;
        }
    }
}
