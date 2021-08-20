package com.macie.common;

import java.io.File;

/**
 * @author Macie
 * @date 2020/11/9 -1:01
 */
public class CommonConstants {

    /** 工程部署路径,如 windows上为 MyBlog_SSM/webapp, 服务器为/MyBlog_SSM的绝对路径  **/
    public static final File WEB_DEPLOY_PATH = new File(System.getProperty("user.dir"));

    /** 工程根目录路径 ,MyBlog_SSM的同级目录 **/
    public static final String WEB_ROOT_PATH = WEB_DEPLOY_PATH.getParent();

    /** 图片文件相对工程根目录的路径 */
    public static final String IMAGE_UPLOAD_PATH = File.separator + "image";

    /** 图片上传路径 **/
    public static final String IMAGE_PATH = WEB_ROOT_PATH + IMAGE_UPLOAD_PATH;
    public static final String IMAGE_UPLOAD_PATH_AVATAR = File.separator + "avatar";
    public static final String IMAGE_UPLOAD_PATH_ARTICLE = File.separator + "article";
}
