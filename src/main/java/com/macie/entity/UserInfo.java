package com.macie.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Macie
 * @date 2020/10/8 -18:32
 */
public class UserInfo {
    private Integer userId;

    @NotBlank(message = "用户名不能为空")
    @Length(min = 3,max = 20,message = "用户名长度为3-20个字符")
    private String userName;

    private String userPassword;

    @Email(message = "邮箱格式不正确")
    private String userEmail;

    private String userPhone;

    private Integer userAge;

    private Integer userGender;

    @Length(min = 3,max = 20,message = "昵称长度为3-20个字符")
    private String userNickName;

    private String userAddress;

    private String userAvatarPath;


    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAge=" + userAge +
                ", userGender=" + userGender +
                ", userAddress='" + userAddress + '\'' +
                ", userNickName='" + userNickName + '\'' +
                ", userAvatar='" + userAvatarPath + '\'' +
                '}';
    }

    public String getUserAvatarPath() {
        return userAvatarPath;
    }

    public void setUserAvatarPath(String userAvatarPath) {
        this.userAvatarPath = userAvatarPath;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
}
