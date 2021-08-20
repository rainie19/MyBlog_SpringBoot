package com.macie.util;

import com.macie.common.CommonConstants;
import com.macie.common.ResponseCode;
import com.macie.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Macie
 * @date 2021/10/10 -0:59
 */
public class ImageUploadUtil {
    private static final Logger log = LoggerFactory.getLogger(ImageUploadUtil.class);

    /**
     * 存储图片
     * @param image
     * @param path
     * @return 存储位置的相对路径
     */
    public static String saveImage(MultipartFile image, String path, String imageName) {
        String userAvatarPath = null;
        if(image == null) {
            return null;
        }
        try {
            String imageSaveDir = CommonConstants.IMAGE_PATH + path;
            String avatarPath = imageSaveDir + File.separator + imageName;
            File destFile = new File(avatarPath);
            //用户修改头像默认覆盖
            if(path != CommonConstants.IMAGE_UPLOAD_PATH_AVATAR) {
                int i = 1;
                // 文件名重复
                while (destFile.exists()) {
                    imageName = i++ + imageName;
                }
                avatarPath = imageSaveDir + File.separator + imageName;
                destFile = new File(avatarPath);
            }
            if(!destFile.getParentFile().exists()) {
                // 目录不存在则创建
                destFile.getParentFile().mkdirs();
            }
            image.transferTo(destFile);
            //返回相对路径
            userAvatarPath = CommonConstants.IMAGE_UPLOAD_PATH + path + File.separator + imageName;
        }catch (Exception e) {
            log.error("文件上传错误", e );
            throw new BusinessException(ResponseCode.UPLOAD_FILE_ERROR);
        }
        return userAvatarPath;
    }
}
