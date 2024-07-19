package com.liy.service;

import com.liy.common.ResponseResult;
import com.liy.entity.ExceptionLog;
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
