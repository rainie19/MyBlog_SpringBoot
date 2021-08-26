package com.macie.controller;

import com.macie.common.ResponseCode;
import com.macie.dto.JsonResponse;
import com.macie.exception.AuthException;
import com.macie.service.UserInfoService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2021/10/10 -0:09
 */
@RestController
public class LoginStatusController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 登录
     * @param userName
     * @param password
     * @param httpSession
     */
    @RequestMapping("/login")
    public JsonResponse login(@NotBlank @Length(min = 3, max = 20) String userName, @NotBlank @Length(min = 5, max = 15) String password, HttpSession httpSession) {
        String decodePwd = userInfoService.getPwdByUserName(userName);
        Map<String, Object> map = new HashMap<>();
        if(decodePwd != null && decodePwd.equals(password)) {
            map.put("token", password);
            httpSession.setAttribute("userName", userName);
            return JsonResponse.responseOK(map);
        }
        else {
            throw new AuthException(ResponseCode.CODE_LOGIN_ERROR);
        }
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public JsonResponse logout() {
        return JsonResponse.responseOK();
    }

}
