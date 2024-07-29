package com.liy.config.file;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.liy.domain.entity.SystemConfig;
import com.liy.domain.entity.SystemFileConfig;
import com.liy.enums.file.QiNiuAreaEnum;
import com.liy.exception.BusinessException;
import com.liy.service.SystemConfigService;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

/**
 * @Author LiY
 *
 * 七牛云 文件处理配置
 */

@Configuration
@RequiredArgsConstructor
@Data
@Slf4j
public class QiNiuYunConfig extends FileConfig{

    private String qi_niu_accessKey;
    private String qi_niu_secretKey;
    private String qi_niu_bucket;
    private String qi_niu_url;
    private String qi_Niu_area;
    private String path;

    private Region region;
    private com.qiniu.storage.Configuration configuration;
    private Auth auth;
    private BucketManager bucketManager;

@Override
public void initOrUpdate(SystemFileConfig systemFileConfig){
        this.qi_niu_accessKey = systemFileConfig.getAccessKey();
        this.qi_niu_secretKey = systemFileConfig.getSecretKey();
        this.qi_niu_bucket = systemFileConfig.getBucket();
        this.qi_niu_url = systemFileConfig.getUrl();
        this.qi_Niu_area = systemFileConfig.getArea();
        this.path = systemFileConfig.getPath();
    initOrUpdateTwo();
    }

    private void initOrUpdateTwo(){
        if(!StrUtil.isAllNotEmpty(qi_niu_accessKey, qi_niu_secretKey, qi_niu_url, qi_Niu_area, qi_niu_bucket)){
            log.error("qi_niu_accessKey:" + qi_niu_accessKey);
            log.error("qi_niu_secretKey:" + qi_niu_secretKey);
            log.error("qi_niu_url:" + qi_niu_url);
            log.error("qi_Niu_area:" + qi_Niu_area);
            log.error("qi_niu_bucket:" + qi_niu_bucket);
            throw new BusinessException("七牛云配置信息缺失");
        }
        this.region = QiNiuAreaEnum.getRegion(qi_Niu_area);
        this.configuration = new com.qiniu.storage.Configuration(region);
        this.auth = Auth.create(qi_niu_accessKey, qi_niu_secretKey);
        this.bucketManager = new BucketManager(auth,configuration);
    }

    private void checkAndInit(){
        if(!ObjectUtil.isAllNotEmpty(region, configuration, auth, bucketManager)){
            initOrUpdateTwo();
        }
    }

    public UploadManager getUploadManager(){
        checkAndInit();
       return new UploadManager(getConfiguration());
    }

    public String getAuthToken(){
        checkAndInit();
        return auth.uploadToken(qi_niu_bucket);
    }

    public String getAuthToken(String qi_niu_bucket){
        checkAndInit();
        return auth.uploadToken(qi_niu_bucket);
    }

    public BucketManager.FileListIterator getDefaultFileListIterator(){
        checkAndInit();
        return bucketManager.createFileListIterator(qi_niu_bucket, null, 1000, null);
    }

    public BucketManager.FileListIterator getFileListIterator(String prefix){
        checkAndInit();
        return bucketManager.createFileListIterator(qi_niu_bucket, prefix, 1000, null);
    }

    public BucketManager.FileListIterator getFileListIterator(String prefix, int limit, String delimiter){
        checkAndInit();
        return bucketManager.createFileListIterator(qi_niu_bucket, prefix, limit, delimiter);
    }

}
