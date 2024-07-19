package com.liy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liy.common.ResponseResult;
import com.liy.entity.Role;

import java.util.List;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author CodeScholar
 * @date 2023年4月9日
 */
public interface RoleService extends IService<Role> {


    ResponseResult listRole(String name);

     ResponseResult insertRole(Role role);

    ResponseResult updateRole(Role role);

    ResponseResult deleteBatch(List<Integer> ids);

    ResponseResult getCurrentUserRole();

    ResponseResult selectById(Integer roleId);

}
