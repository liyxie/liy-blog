package com.liy.strategy.imp.file;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.liy.config.file.ALiConfig;
import com.liy.config.file.FileConfig;
import com.liy.domain.entity.SystemConfig;
import com.liy.domain.entity.SystemFileConfig;
import com.liy.service.SystemConfigService;
import com.liy.strategy.FileUploadStrategy;
import com.liy.util.FileUtils;
import com.liy.util.UUIDUtils;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Service("aliUploadStrategyImpl")
@RequiredArgsConstructor
public class AliUploadStrategyImpl implements FileUploadStrategy {

    private final Logger logger = LoggerFactory.getLogger(AliUploadStrategyImpl.class);

    private final ALiConfig aLiConfig;

    @Override
    public FileUploadStrategy updateConfig(SystemFileConfig systemFileConfig) {
        aLiConfig.initOrUpdate(systemFileConfig);
        return this;
    }


    @Override
    public String fileUpload(String path, MultipartFile file) {
        String suffix = null;
        String key = null;

        // 创建OSS实例
        OSS ossClient = aLiConfig.getDefaultOSS();

        //获取上传文件输入流
        InputStream inputStream = null;
        try {
            suffix = FileUtils.getExtension(file.getInputStream());
            inputStream = file.getInputStream();
            //在文件名称里面添加随机唯一值（因为如果上传文件名称相同的话，后面的文件会将前面的文件给覆盖了）
            String newFileName = aLiConfig.getPath() + path + UUIDUtils.getUuid() + "." + suffix;

            // 调用oss方法实现上传
            // 参数一：Bucket名称  参数二：上传到oss文件路径和文件名称  比如 /aa/bb/1.jpg 或者直接使用文件名称  参数三：上传文件的流
            ossClient.putObject(aLiConfig.getAli_bucket(), newFileName, inputStream);

            //把上传之后的文件路径返回，需要把上传到阿里云路径返回    https://edu-guli-eric.oss-cn-beijing.aliyuncs.com/1.jpg
            key = "https://" + aLiConfig.getAli_bucket() + "." + aLiConfig.getAli_endpoint() + "/" + newFileName;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
            if (inputStream != null) {
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
     * 删除文件 -- 阿里云OSS
     *
     * @param key   文件url
     * @return      ResponseResult
     */
    @Override
    public Boolean deleteFile(String ...key) {

        // 创建OSS实例
        OSS ossClient = aLiConfig.getDefaultOSS();

        try {
            // 判断文件是否存在。如果返回值为true，则文件存在，否则存储空间或者文件不存在。
            boolean found = ossClient.doesObjectExist(aLiConfig.getAli_bucket(), Arrays.toString(key));
            if (found) {
                // 文件存在，删除文件
                ossClient.deleteObject(aLiConfig.getAli_bucket(), Arrays.toString(key));
                return true;
            } else {
                return false;
            }
        } catch (OSSException oe) {
            // 获取OSS异常
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            return false;
        } finally {
            if (ossClient != null) {
                // 最终一定要执行关闭OSSClient
                ossClient.shutdown();
            }
        }

    }

}
