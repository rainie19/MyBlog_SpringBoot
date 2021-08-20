package com.macie.config.handler;

import com.macie.dto.JsonResponse;
import com.macie.exception.AuthException;
import com.macie.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @author Macie
 * @date 2021/7/2 -15:45
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public JsonResponse handleBusinessException(BusinessException e) {
        log.error("业务出错, code={}", e.getResponseCode().getCode(), e);
        return JsonResponse.response(e.getResponseCode());
    }

    @ExceptionHandler({TypeMismatchException.class, ConversionFailedException.class})
    public JsonResponse handleArgumentConvertException(Exception e) {
        log.error("参数转换失败", e);
        return JsonResponse.responseValidationFailed("参数错误");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数校验错误", e);
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage()).append(", ");
        }
        return JsonResponse.responseValidationFailed(sb.toString());
    }

    @ExceptionHandler(BindException.class)
    public JsonResponse handleBindException(BindException e) {
        log.error("参数校验错误", e);
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getDefaultMessage()).append(", ");
        }
        return JsonResponse.responseValidationFailed(sb.toString());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public JsonResponse handleConstraintViolationException(ConstraintViolationException e) {
        log.error("参数校验错误", e);
        String[] split = e.getMessage().split("[,:]");
        return JsonResponse.responseValidationFailed(e.getMessage().split("[,:]")[1]);
    }


    @ExceptionHandler(AuthException.class)
    public JsonResponse handleAuthException(AuthException e) {
        log.error("身份验证出错, code={}",e.getResponseCode().getCode(), e);
        return JsonResponse.response(e.getResponseCode());
    }

    @ExceptionHandler
    public JsonResponse handleUnknownException(Exception e) {
        log.error("发生未知错误", e);
        return JsonResponse.responseFailed("未知错误，请联系网站管理员！");
    }
}
