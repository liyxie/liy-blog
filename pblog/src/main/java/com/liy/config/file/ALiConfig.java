package com.liy.config.file;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.liy.domain.entity.SystemFileConfig;
import com.liy.exception.BusinessException;
import com.liy.service.SystemConfigService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @Author LiY
 *
 * 阿里云文件上传配置
 */

@Configuration
@Data
@Slf4j
public class ALiConfig extends FileConfig{

    private String ali_accessKey;
    private String ali_secretKey;
    private String ali_endpoint;
    private String ali_bucket;
    private String path;

    private ClientBuilderConfiguration conf;

    @Override
    public void initOrUpdate(SystemFileConfig systemFileConfig) {
        this.ali_accessKey = systemFileConfig.getAccessKey();
        this.ali_secretKey = systemFileConfig.getSecretKey();
        this.ali_endpoint = systemFileConfig.getUrl();
        this.ali_bucket = systemFileConfig.getBucket();
        this.path = systemFileConfig.getPath();
        initOrUpdateTwo();
    }

    void initOrUpdateTwo(){
        if(!StrUtil.isAllNotEmpty(ali_accessKey, ali_secretKey, ali_bucket, ali_endpoint)){
            throw new BusinessException("阿里云配置信息缺失");
        }
        this.conf = getClientBuilderConfiguration();
    }


    private ClientBuilderConfiguration getClientBuilderConfiguration() {
        // 创建ClientConfiguration。ClientConfiguration是OSSClient的配置类，可配置代理、连接超时、最大连接数等参数。
        ClientBuilderConfiguration conf = new ClientBuilderConfiguration();
        // 设置OSSClient允许打开的最大HTTP连接数，默认为1024个。
        conf.setMaxConnections(200);
        // 设置Socket层传输数据的超时时间，默认为50000毫秒。
        conf.setSocketTimeout(10000);
        // 设置建立连接的超时时间，默认为50000毫秒。
        conf.setConnectionTimeout(10000);
        // 设置从连接池中获取连接的超时时间（单位：毫秒），默认不超时。
        conf.setConnectionRequestTimeout(1000);
        // 设置连接空闲超时时间。超时则关闭连接，默认为60000毫秒。
        conf.setIdleConnectionTime(60000);
        // 设置失败请求重试次数，默认为3次。
        conf.setMaxErrorRetry(3);
        // 设置是否支持将自定义域名作为Endpoint，默认支持。
        conf.setSupportCname(true);
        // 设置是否开启HTTP重定向，默认开启。
        conf.setRedirectEnable(true);
        // 设置是否开启SSL证书校验，默认开启。
        conf.setVerifySSLEnable(false);
        return conf;
    }

    public OSS getDefaultOSS(){
        OSS ossClient = new OSSClientBuilder().build(ali_endpoint, ali_accessKey, ali_secretKey, conf);
        return ossClient;
    }

}
