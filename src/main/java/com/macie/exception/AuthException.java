package com.macie.exception;

import com.macie.common.ResponseCode;

/**
 * 身份验证异常
 * @author Macie
 * @date 2021/7/2 -23:59
 */
public class AuthException extends RuntimeException{
    private ResponseCode responseCode;
    public AuthException(ResponseCode code) {
        super(code.getMessage());
        this.responseCode = code;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
