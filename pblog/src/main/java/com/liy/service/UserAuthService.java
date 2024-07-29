package com.liy.service;

import com.liy.domain.ResponseResult;
import com.liy.domain.entity.UserAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liy.domain.dto.EmailLoginDTO;
import com.liy.domain.dto.EmailRegisterDTO;
import com.liy.domain.dto.QQLoginDTO;
import com.liy.domain.dto.UserAuthDTO;

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
