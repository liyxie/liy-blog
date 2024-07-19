package com.liy.service;

import com.liy.common.ResponseResult;
import com.liy.entity.UserAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liy.dto.EmailLoginDTO;
import com.liy.dto.EmailRegisterDTO;
import com.liy.dto.QQLoginDTO;
import com.liy.dto.UserAuthDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CodeScholar
 * @date 2023年4月9日
 */
public interface UserAuthService extends IService<UserAuth> {

    ResponseResult emailRegister(EmailRegisterDTO emailRegisterDTO);

    ResponseResult updatePassword(EmailRegisterDTO emailRegisterDTO);

    ResponseResult emailLogin(EmailLoginDTO emailLoginDTO);

    ResponseResult qqLogin(QQLoginDTO qqLoginDTO);

    ResponseResult weiboLogin(String code);

    ResponseResult giteeLogin(String code);

    ResponseResult sendEmailCode(String email);

    ResponseResult bindEmail(UserAuthDTO vo);

    ResponseResult updateUser(UserAuthDTO vo);

}
