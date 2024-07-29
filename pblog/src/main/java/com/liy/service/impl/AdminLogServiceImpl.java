package com.liy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liy.domain.ResponseResult;
import com.liy.common.FieldConstants;
import com.liy.domain.entity.AdminLog;
import com.liy.mapper.AdminLogMapper;
import com.liy.service.AdminLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liy.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author CodeScholar
 * @date 2023年4月9日
 */
@Service
public class AdminLogServiceImpl extends ServiceImpl<AdminLogMapper, AdminLog> implements AdminLogService {

    /**
     * 分页查询操作日志
     *
     * @return
     */
    @Override
    public ResponseResult listAdminLog() {
        Page<AdminLog> sysLogPage = baseMapper.selectPage(new Page<>(PageUtils.getPageNo(), PageUtils.getPageSize()),
                new QueryWrapper<AdminLog>().orderByDesc(FieldConstants.CREATE_TIME));
        return ResponseResult.success(sysLogPage);
    }

    /**
     * 批量删除操作日志
     *
     * @param ids 操作日志id集合
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteAdminLog(List<Long> ids) {
        int rows = baseMapper.deleteBatchIds(ids);
        return rows > 0 ? ResponseResult.success() : ResponseResult.error("批量删除操作日志失败");
    }
}
