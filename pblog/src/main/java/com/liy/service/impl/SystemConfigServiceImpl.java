package com.liy.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liy.common.RedisConstants;
import com.liy.domain.ResponseResult;
import com.liy.domain.dto.UpdateSystemConfigDTO;
import com.liy.domain.entity.SystemConfig;
import com.liy.domain.entity.SystemFileConfig;
import com.liy.domain.entity.User;
import com.liy.domain.vo.system.SystemConfigVO;
import com.liy.domain.vo.system.SystemFileConfigVO;
import com.liy.mapper.SystemConfigMapper;
import com.liy.mapper.SystemFileConfigMapper;
import com.liy.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.liy.common.Constants.USER_ROLE_ID;
import static com.liy.common.FieldConstants.ID;
import static com.liy.common.FieldConstants.LIMIT_ONE;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author CodeScholar
 * @date 2023年4月9日
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {


    private final UserService userService;

    private final RedisService redisService;

    private final CloudOssService cloudOssService;

    private final SystemFileConfigService systemFileConfigService;

    @Override
    @PostConstruct
    public void updateCache() {
        SystemConfig systemConfig = getCustomizeOne();
        SystemFileConfig systemFileConfig = systemFileConfigService.getById(systemConfig.getFileUploadWay());

        redisService.setCacheObject(RedisConstants.System_Config_Key,systemConfig);
        redisService.setCacheObject(RedisConstants.System_Config_File_key, systemFileConfig);

        // 更新本地文件存储
        cloudOssService.updateFileUploadWay(getSysFileConfig());
    }

    @Override
    public SystemConfig getSysConfig() {
        return redisService.hasKey(RedisConstants.System_Config_Key) ?
                redisService.getCacheObject(RedisConstants.System_Config_Key)
                : getCustomizeOne();
    }

    /**
     * 获取系统配置
     * @return
     */
    @Override
    public ResponseResult getConfig() {
        // 系统配置信息
        QueryWrapper<SystemConfig> queryWrapper = new QueryWrapper<>();
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        if (user.getRoleId() > USER_ROLE_ID) queryWrapper.orderByDesc(ID);
        queryWrapper.last(LIMIT_ONE);
        SystemConfig systemConfig = baseMapper.selectOne(queryWrapper);

        // 文件存储配置信息
        List<SystemFileConfig> systemFileConfigList = systemFileConfigService.list(null);

        SystemConfigVO systemConfigVO = SystemConfigVO.poToVo(systemConfig);
        List<SystemFileConfigVO> systemFileConfigVOList = systemFileConfigList.stream().filter(Objects::nonNull).map(SystemFileConfigVO::poToVo).collect(Collectors.toList());
        systemConfigVO.setSystemFileConfigVOList(systemFileConfigVOList);

        return ResponseResult.success(systemConfigVO);
    }

    /**
     * 修改系统配置
     * @param UpdateSystemConfigDTO
     * @return ResponseResult
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updateConfig(UpdateSystemConfigDTO systemConfig) {
        SystemConfig newSystemConfig = UpdateSystemConfigDTO.DtoToPo(systemConfig);
        int i = baseMapper.updateById(newSystemConfig);
        List<SystemFileConfig> systemFileConfigList = systemConfig.getSystemFileConfigVOList().stream()
                .filter(Objects::nonNull)
                .map(SystemFileConfigVO::voToPo)
                .collect(Collectors.toList());

//        List<SystemFileConfig>  systemFileConfigList= new ArrayList<>();
        SystemFileConfig systemFileConfig = new SystemFileConfig();
        systemFileConfig.setId(1);
        systemFileConfig.setSecretKey("1111111");
//        systemFileConfigList.add(systemFileConfig);
//        systemFileConfigMapper.MyUpdateById(systemFileConfig);
        systemFileConfigService.updateBatchById(systemFileConfigList);
        log.info("更新缓存");
        // 更新缓存
        updateCache();
        // 更新文件Service
        cloudOssService.updateFileUploadWay(getSysFileConfig());

        return ResponseResult.check(i);
    }

    //---------自定义方法----------
    @Override
    public SystemConfig getCustomizeOne() {
        return baseMapper.selectOne(new QueryWrapper<SystemConfig>().last(LIMIT_ONE));
    }

    @Override
    public SystemFileConfig getSysFileConfig() {
        return redisService.hasKey(RedisConstants.System_Config_File_key) ?
                redisService.getCacheObject(RedisConstants.System_Config_File_key)
                : getSystemFileConfig();
    }
    @Override
    public SystemFileConfig getSystemFileConfig() {
        return systemFileConfigService.getById(getCustomizeOne().getFileUploadWay());
    }

    @Override
    public SystemFileConfig getSystemFileConfig(Integer id) {
        return systemFileConfigService.getById(id);
    }

    @Override
    public String getNewFileStrategy() {
        return getSystemFileConfig().getStrategy();
    }
}
