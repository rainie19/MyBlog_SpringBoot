package com.macie.common;

/**
 * HTTP RESPONSE CODE
 * @author Macie
 * @date 2021/7/2 -22:04
 */
public enum ResponseCode {
    CODE_SUCCESS(20000, null),
    CODE_FAILED(20001,"操作失败"),

    /** business error */
    CODE_BUSINESS_ERROR(30001, "业务错误"),
    DATA_VALIDATION_FAILED(30002,"数据校验失败"),
    ARTICLE_NOT_EXIST(30003, "文章不存在"),
    WRONG_ARTICLE_OPERATION(30004, "文章操作错误"),
    UPLOAD_FILE_ERROR(30010, "文件上传失败"),
    USERPROFILE_UPDATE_ERROR(30011, "用户信息更新失败"),
    USERACOUNT_OLDPWD_ERROR(30012, "原密码错误"),
    USERACOUNT_UPDATE_ERROR(30013, "账号信息更新失败"),

    /** access error */
    CODE_LOGIN_ERROR(50007, "账号或密码错误"),
    CODE_ILLEGAL_TOKEN(50008, "无权限进行此操作"),
    CODE_EXPIRED_TOKEN(50014, "登录过期！"),
    CODE_CHANGED_ACCOUNT(50018, "请切换账号再试"),
    ;

    private Integer code;
    private String message;

    ResponseCode(Integer code, String message) {
        this.code =code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
