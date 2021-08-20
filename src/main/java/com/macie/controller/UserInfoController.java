package com.macie.controller;

import com.macie.common.CommonConstants;
import com.macie.dto.JsonResponse;
import com.macie.entity.UserInfo;
import com.macie.service.UserInfoService;
import com.macie.util.ImageUploadUtil;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2021/10/9 -22:31
 */
@RestController
@Transactional
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 获取用户个人信息
     *
     * @param userName
     * @return
     */
    @RequestMapping("/getUserInfo")
    public JsonResponse getUserInfo(@NotBlank @Length(min = 3, max = 20) String userName) {
        Map<String, Object> map = new HashMap<>();
        UserInfo userInfo = userInfoService.getUserInfoByUserName(userName);
        map.put("userInfo", userInfo);
        return JsonResponse.responseOK(map);
    }

    /**
     * 修改用户个人信息
     *
     */
    @PostMapping("/modifyUserInfo")
    public JsonResponse modifyUserInfo(UserInfo userInfo, @NotBlank String type, MultipartFile userAvatar,
                                       String oldUserName, String oldPassWd, String newPassWd) {
        if("profile".equals(type)) {
            userInfoService.updateUserInfo(userInfo, oldUserName);
            if(userAvatar != null && !userAvatar.isEmpty()) {
                String fileName = userAvatar.getOriginalFilename();
                // 获取后缀名
                int index = fileName.lastIndexOf('.');
                String imgType = fileName.substring(index);
                ImageUploadUtil.saveImage(userAvatar, CommonConstants.IMAGE_UPLOAD_PATH_AVATAR, userInfo.getUserName()+imgType);
            }
        }
        else if("account".equals(type)) {
            userInfoService.updatePassWord(userInfo.getUserName(), oldPassWd, newPassWd);
        }
        return JsonResponse.responseOK();
    }
}
