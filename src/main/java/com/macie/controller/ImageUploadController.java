package com.macie.controller;

import com.macie.common.CommonConstants;
import com.macie.dto.JsonResponse;
import com.macie.util.ImageUploadUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Macie
 * @date 2021/10/10 -0:17
 */
@RestController
@Transactional
public class ImageUploadController {

    @PostMapping("/uploadImg")
    public JsonResponse uploadImg(@RequestParam("image") MultipartFile image) {
        String fileName = image.getOriginalFilename();
        // 获取后缀名
        int index = fileName.lastIndexOf('.');
        String imgName = fileName.substring(0, index);
        String imgType = fileName.substring(index);

        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("_yyyy_MM_dd_");
        String formatTime = simpleDateFormat.format(currentTime);

        String filename = imgName + formatTime + currentTime.getTime() + imgType;

        String imageUrl = ImageUploadUtil.saveImage(image, CommonConstants.IMAGE_UPLOAD_PATH_ARTICLE, filename);
        imageUrl = imageUrl.substring(1);
        Map<String, Object> map = new HashMap();
        map.put("imageUrl", imageUrl);
        return JsonResponse.responseOK(map);
    }
}
