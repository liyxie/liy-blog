package com.liy.service;

import com.liy.common.ResponseResult;
import com.liy.entity.FeedBack;
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
public interface FeedBackService extends IService<FeedBack> {

    ResponseResult listFeedBack(Integer type);

    ResponseResult deleteBatch(List<Integer> ids);


    ResponseResult insertFeedback(FeedBack feedBack);

}
