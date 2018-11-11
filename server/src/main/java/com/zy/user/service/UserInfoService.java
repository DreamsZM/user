package com.zy.user.service;

import com.zy.user.pojo.UserInfo;

public interface UserInfoService {

    UserInfo findByOpenid(String openid);
}
