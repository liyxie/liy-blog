package com.liy.config.file;

import com.liy.domain.entity.SystemFileConfig;
import com.liy.service.SystemConfigService;

/**
 * @Author LiY
 *
 * 文件存储配置父类
 */
public abstract class FileConfig {

    public abstract void initOrUpdate(SystemFileConfig systemFileConfig);
}
