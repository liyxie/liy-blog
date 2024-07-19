package com.liy.service;

import com.liy.common.ResponseResult;
import com.liy.entity.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeScholar
 * @date 2023年4月9日
 */
public interface PageService extends IService<Page> {

    ResponseResult listPage();

    ResponseResult insertPage(Page page);

    ResponseResult updatePage(Page page);

    ResponseResult deletePageById(Long id);
}
