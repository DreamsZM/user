package com.zy.user.repository;

import com.zy.user.pojo.UserInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoRepositoryTest {

    @Autowired
    private UserInfoRepository repository;

    @Test
    public void saveTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setId("1");
        userInfo.setUsername("zy");
        userInfo.setPassword("809903");
        userInfo.setOpenid("abc");
        userInfo.setRole(1);
        UserInfo result = repository.save(userInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenid() {
        UserInfo userInfo = repository.findByOpenid("abc");
        Assert.assertNotNull(userInfo);
    }
}