package com.macie.service.serviceImpl;

import com.macie.common.ResponseCode;
import com.macie.dao.UserInfoDao;
import com.macie.entity.UserInfo;
import com.macie.exception.BusinessException;
import com.macie.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Macie
 * @date 2020/9/30 -21:08
 */
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    public UserInfoDao userInfoDao;

    @Override
    public UserInfo getUserInfoByUserName(String userName) {
        return userInfoDao.getUserInfoByUserName(userName);
    }

    @Override
    public String getPwdByUserName(String userName) {
        return userInfoDao.getPwdByUserName(userName);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo, String oldUserName) {
        if(userInfoDao.updateUserInfo(userInfo, oldUserName) == 0) {
            throw new BusinessException(ResponseCode.USERPROFILE_UPDATE_ERROR);
        }
    }

    @Override
    public void updatePassWord(String userName, String oldPwd, String newPwd) {
        String currentPwd = userInfoDao.getPwdByUserName(userName);
        if(!currentPwd.equals(oldPwd)) {
            throw new BusinessException(ResponseCode.USERACOUNT_OLDPWD_ERROR);
        }
        if(userInfoDao.updatePwd(userName, newPwd) == 0) {
            throw new BusinessException(ResponseCode.USERACOUNT_UPDATE_ERROR);
        }
    }
}
