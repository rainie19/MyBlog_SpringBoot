package com.macie.dto;

import com.macie.common.ResponseCode;

/**
 * json数据包装类
 * @author Macie
 * @date 2020/10/29 -11:20
 */
public class JsonResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public JsonResponse(Integer code) {
        this.code = code;
    }

    private JsonResponse(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    private JsonResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    private JsonResponse(Integer code, String msg, T data) {
        this.code = code;
        this.message = msg;
        this.data = data;
    }
    public static <T> JsonResponse<T> responseOK() {
        return new JsonResponse<>(ResponseCode.CODE_SUCCESS.getCode());
    }

    public static <T> JsonResponse<T> responseOK(T data) {
        return new JsonResponse<>(ResponseCode.CODE_SUCCESS.getCode(), data);
    }
    public static <T> JsonResponse<T> responseFailed(String msg) {
        return new JsonResponse<>(ResponseCode.CODE_FAILED.getCode(), msg);
    }
    public static <T> JsonResponse<T> responseValidationFailed(String msg) {
        return new JsonResponse<>(ResponseCode.DATA_VALIDATION_FAILED.getCode(), msg);
    }

    public static <T> JsonResponse<T> response(ResponseCode code) {
        return new JsonResponse<>(code.getCode(), code.getMessage());
    }

    public static <T> JsonResponse<T> response(ResponseCode code, T data) {
        return new JsonResponse<>(code.getCode(), code.getMessage(), data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "JsonResponse{" +
                "code=" + code +
                ", msg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
