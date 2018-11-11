package com.zy.user.controller;

import com.zy.user.constants.CookieConstant;
import com.zy.user.constants.RedisConstant;
import com.zy.user.enums.RoleEnum;
import com.zy.user.enums.ResultEnum;
import com.zy.user.pojo.UserInfo;
import com.zy.user.service.UserInfoService;
import com.zy.user.utils.CookieUtil;
import com.zy.user.utils.ResultVOUtil;
import com.zy.user.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/buyer")
    public ResultVO buyer(@RequestParam("openid") String openid, HttpServletResponse response){
        //从数据库查找用户信息
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        //验证
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        if (userInfo.getRole() != RoleEnum.Buyer.getCode()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        //验证通过后，设置cookie
        CookieUtil.set(response, CookieConstant.OPENID, openid, CookieConstant.expire);
        return ResultVOUtil.success();
    }

    @GetMapping("/seller")
    public ResultVO seller(@RequestParam("openid") String openid, HttpServletRequest request, HttpServletResponse response){
        //判断是否已经登录
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null &&
                !StringUtils.isEmpty(redisTemplate.opsForValue()
                        .get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))){
            return ResultVOUtil.success();
        }
        //从数据库查找用户信息
        UserInfo userInfo = userInfoService.findByOpenid(openid);
        //验证
        if (userInfo == null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }

        if (userInfo.getRole() != RoleEnum.Seller.getCode()){
            return ResultVOUtil.error(ResultEnum.ROLE_ERROR);
        }

        //写入redis中
        String uuid = java.util.UUID.randomUUID().toString();
        String token = String.format(RedisConstant.TOKEN_TEMPLATE, uuid);
        redisTemplate.opsForValue().set(token, openid, CookieConstant.expire, TimeUnit.SECONDS);

        //验证通过后，设置cookie
        CookieUtil.set(response, CookieConstant.TOKEN, uuid, CookieConstant.expire);
        return ResultVOUtil.success();
    }
}
