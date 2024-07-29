package com.liy.service;

import com.liy.domain.ResponseResult;
import com.liy.domain.entity.ExceptionLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeScholar
 * @date 2023年4月9日
 */
public interface ExceptionLogService extends IService<ExceptionLog> {

    ResponseResult listExceptionLog();

    ResponseResult deleteBatch(List<Long> ids);
}
