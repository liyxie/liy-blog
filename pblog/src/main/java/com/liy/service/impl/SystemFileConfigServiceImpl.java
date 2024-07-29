package com.liy.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
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
 * @author liy
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class SystemFileConfigServiceImpl extends ServiceImpl<SystemFileConfigMapper, SystemFileConfig> implements SystemFileConfigService {



}
