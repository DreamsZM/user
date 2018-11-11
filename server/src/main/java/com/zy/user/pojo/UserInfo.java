package com.zy.user.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserInfo {

    @Id
    private String id;

    private String username;

    private String password;

    private String openid;

    private int role;
}
