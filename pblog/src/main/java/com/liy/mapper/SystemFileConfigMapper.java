package com.liy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liy.domain.entity.SystemFileConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统文件存储配置表 Mapper 接口
 * </p>

 */

@Mapper
public interface SystemFileConfigMapper extends BaseMapper<SystemFileConfig> {

    void updateBatchById(@Param("systemFileConfigList") List<SystemFileConfig> systemFileConfigList);

    void MyUpdateById(@Param("systemFileConfig") SystemFileConfig systemFileConfig);

}
