package com.macie.dao;

import com.macie.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Macie
 * @date 2020/9/30 -20:43
 */
@Mapper
public interface UserInfoDao {
    /**
     * 获取用户信息
     *
     * @param userName
     * @return
     */
    public UserInfo getUserInfoByUserName(String userName);

    /**
     * 获取密码
     *
     * @param userName
     * @return
     */
    public String getPwdByUserName(String userName);

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @param oldUserName
     * @return
     */
    public int updateUserInfo(@Param("userInfo") UserInfo userInfo, @Param("oldUserName") String oldUserName);

    /**
     * 更新密码
     *
     * @param userName
     * @param newPwd
     * @return
     */
    public int updatePwd(@Param("userName") String userName, @Param("newPwd") String newPwd);
}
