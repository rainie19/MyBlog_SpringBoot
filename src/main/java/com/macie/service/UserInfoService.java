package com.macie.service;

import com.macie.entity.UserInfo;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author Macie
 * @date 2020/11/2 -20:52
 */
@Validated
public interface UserInfoService {

    /**
     * 获取用户信息
     *
     * @param userName
     * @return
     */
    UserInfo getUserInfoByUserName(@NotBlank @Length(min = 3, max = 20) String userName);

    /**
     * 获取密码
     *
     * @param userName
     * @return
     */
    String getPwdByUserName(@NotBlank @Length(min = 3, max = 20) String userName);

    /**
     * 更新用户信息
     *  @param userInfo
     * @param oldUserName
     * */
    void updateUserInfo(@Valid UserInfo userInfo, @NotBlank @Length(min = 5, max = 15) String oldUserName);

    /**
     * 更新用户密码
     * @param userName
     * @param oldPwd
     * @param newPwd
     */
    void updatePassWord(@NotBlank @Length(min = 3, max = 20) String userName,
                        @NotBlank @Length(min = 5, max = 15) String oldPwd,
                        @NotBlank @Length(min = 5, max = 15) String newPwd);
}
