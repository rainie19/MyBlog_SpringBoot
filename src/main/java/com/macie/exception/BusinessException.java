package com.macie.exception;

import com.macie.common.ResponseCode;

/**
 * 业务异常
 * @author Macie
 * @date 2021/7/3 -18:03
 */
public class BusinessException extends RuntimeException {
    private ResponseCode responseCode;

    public BusinessException(ResponseCode code) {
        super(code.getMessage());
        this.responseCode = code;
    }

    public BusinessException(ResponseCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.responseCode = code;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }
}
