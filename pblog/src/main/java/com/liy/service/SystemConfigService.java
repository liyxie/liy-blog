package com.liy.service;

import com.liy.domain.ResponseResult;
import com.liy.domain.dto.UpdateSystemConfigDTO;
import com.liy.domain.entity.SystemConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liy.domain.entity.SystemFileConfig;

/**
 * <p>
 * 系统配置表 服务类
 * </p>
 *
 * @author CodeScholar
 * @date 2023年4月9日
 */
public interface SystemConfigService extends IService<SystemConfig> {

    /**
     *  缓存配置信息
     */
    void updateCache();

    /**
     * 内部获取配置信息
     * @return
     */
    SystemConfig getSysConfig();

    ResponseResult getConfig();

    ResponseResult updateConfig(UpdateSystemConfigDTO systemConfig);

    SystemConfig getCustomizeOne();

    /**
     * 获取文件存储配置信息(Mysql)
     */
    SystemFileConfig getSystemFileConfig();

    /**
     *  获取文件配置信息（优先缓存）
     * @return SystemFileConfig
     */
    SystemFileConfig getSysFileConfig();

    SystemFileConfig getSystemFileConfig(Integer id);

    String getNewFileStrategy();
}
