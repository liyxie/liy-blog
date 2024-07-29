package com.liy.config.file;

import com.liy.domain.entity.SystemConfig;
import com.liy.domain.entity.SystemFileConfig;
import com.liy.service.SystemConfigService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * @Author LiY
 *
 * 本地文件处理配置
 */

@EqualsAndHashCode(callSuper = true)
@Configuration
@RequiredArgsConstructor
@Data
public class LocalConfig extends FileConfig {

    private String localFileUrl;



    @Override
    public void initOrUpdate(SystemFileConfig systemFileConfig) {
        localFileUrl = systemFileConfig.getPath();
    }
}
