package com.liy.strategy.imp.file;

import com.liy.config.file.TXYunConfig;
import com.liy.domain.entity.SystemFileConfig;
import com.liy.exception.BusinessException;
import com.liy.exception.GlobalException;
import com.liy.strategy.FileUploadStrategy;
import com.liy.util.FileUtils;
import com.liy.util.StringUtils;
import com.liy.util.UUIDUtils;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author LiY
 *
 * 腾讯云存储 处理文件
 */

@Service("tXUploadStrategyImpl")
@RequiredArgsConstructor
public class TXUploadStrategyImpl implements FileUploadStrategy {

    private final TXYunConfig txYunConfig;

    @Override
    public FileUploadStrategy updateConfig(SystemFileConfig systemFileConfig) {
        txYunConfig.initOrUpdate(systemFileConfig);
        return this;
    }

    @Override
    public String fileUpload(String path, MultipartFile file) {
        String suffix = null;
        // 指定文件将要存放的存储桶
        String bucketName = txYunConfig.getTxYunBucket();
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为 folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = null;
        InputStream inputStream = null;
        ObjectMetadata objectMetadata = null;
        try {
            suffix = FileUtils.getExtension(file.getInputStream());
            key = UUIDUtils.getUuid() + "." + suffix;
            key = StringUtils.splicingUrl('/', txYunConfig.getPath(), path, key);
            inputStream = file.getInputStream();
            objectMetadata = txYunConfig.getObjectMetadata(inputStream, suffix);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
            PutObjectResult putObjectResult = txYunConfig.getDefaultCOSClient().putObject(putObjectRequest);
            key = StringUtils.splicingUrl('/', txYunConfig.getTxYunUrl(), key);
        }catch (IOException e){
            e.printStackTrace();
            throw new BusinessException("tx文件上传错误");
        }catch (Exception e){
            throw new BusinessException("腾讯云文件存储错误: " + e.getMessage());
        }
        return key;
    }

    @Override
    public Boolean deleteFile(String... fileName) {
        return null;
    }
}
