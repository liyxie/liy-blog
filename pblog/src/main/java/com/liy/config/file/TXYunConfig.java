package com.liy.config.file;

import cn.hutool.core.util.StrUtil;
import com.liy.domain.entity.SystemConfig;
import com.liy.domain.entity.SystemFileConfig;
import com.liy.enums.file.TXAreaEnum;
import com.liy.exception.BusinessException;
import com.liy.service.SystemConfigService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author LiY
 *
 * 腾讯云存储 配置
 */

@Configuration
@RequiredArgsConstructor
@Data
@Slf4j
public class TXYunConfig extends FileConfig {

    private String txYunUrl;
    private String txYunBucket;
    private String txYunAddr;
    private String txYunSecretId;
    private String txYunSecretKey;
    private String path;


    private COSCredentials cred;
    private Region region;
    private ClientConfig clientConfig;

    @Override
    public void initOrUpdate(SystemFileConfig systemFileConfig){
        this.txYunUrl = systemFileConfig.getUrl();
        this.txYunBucket = systemFileConfig.getBucket();
        this.txYunAddr = systemFileConfig.getArea();
        this.txYunSecretId = systemFileConfig.getAccessKey();
        this.txYunSecretKey = systemFileConfig.getSecretKey();
        this.path = systemFileConfig.getPath();
        initOrUpdateTwo();
    }

    private void initOrUpdateTwo(){
        if(!StrUtil.isAllNotEmpty(txYunUrl, txYunBucket, txYunAddr, txYunSecretId, txYunSecretKey)){
            log.error("txYunUrl:" + txYunUrl);
            log.error("txYunBucket:" + txYunBucket);
            log.error("txYunAddr:" + txYunAddr);
            log.error("txYunSecretId:" + txYunSecretId);
            log.error("txYunSecretKey:" + txYunSecretKey);
            throw new BusinessException("腾讯云配置信息缺失");
        }
        cred = new BasicCOSCredentials(txYunSecretId, txYunSecretKey);
        this.region = new Region(TXAreaEnum.getRegion(txYunAddr));
        this.clientConfig = new ClientConfig(region);
    }

    /**
     *  获取默认配置的COSClient
     * @return COSClient
     */
    public COSClient getDefaultCOSClient(){
        clientConfig.setHttpProtocol(HttpProtocol.https);
        return new COSClient(cred, clientConfig);
    }

    public ObjectMetadata getObjectMetadata(InputStream inputStream, String fileType) throws IOException {
        // 创建上传Object的Metadata
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // - 使用输入流存储，需要设置请求长度
        objectMetadata.setContentLength(inputStream.available());
        // - 设置缓存
        objectMetadata.setCacheControl("no-cache");
        // - 设置Content-Type
        objectMetadata.setContentType(fileType);
        return objectMetadata;
    }




}
