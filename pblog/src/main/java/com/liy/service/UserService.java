package com.liy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liy.domain.ResponseResult;
import com.liy.domain.entity.User;

import java.util.List;
import java.util.Map;

/**
 *
 * @author CodeScholar
 * @date: 2023年4月9日
 */
public interface UserService extends IService<User> {

    ResponseResult listUser(String username, Integer loginType);

    ResponseResult getUserById(Integer id);

    ResponseResult insertUser(User user);

    ResponseResult updateUser(User user);

    ResponseResult deleteBatch(List<Integer> ids);

    ResponseResult getCurrentUserInfo();

    ResponseResult getCurrentUserMenu();

    ResponseResult updatePassword(Map<String, String> map);

    ResponseResult listOnlineUsers(String keywords);

    ResponseResult kick(String token);
}
