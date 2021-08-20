package com.macie.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.macie.common.ResponseCode;
import com.macie.dto.JsonResponse;
import com.macie.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Macie
 * @date 2021/7/2 -20:55
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean isLogin = false;
        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("userName");
        if(userName != null) {
            Cookie[] cookies = request.getCookies();
            String uri = request.getRequestURI();
            String passWd = userInfoService.getPwdByUserName(userName);
            for (Cookie c : cookies) {
                if ("blogToken".equals(c.getName()) && c.getValue().equals(passWd)) {
                    isLogin = true;
                    break;
                }
            }
        }
        if(isLogin) {
            return true;
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonResult = mapper.writeValueAsString(JsonResponse.response(ResponseCode.CODE_ILLEGAL_TOKEN));
        response.getWriter().println(jsonResult);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
