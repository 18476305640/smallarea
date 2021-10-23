package com.zjazn.uaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author testjava
 * @since 2021-06-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String id;
    private String username;
    private String wx_openid;
    private String password;
    private String fullname;
    private String email;
    private String headimg;
    private String personality;
    private Date birth;
    private Date createTime;
    private Date updateTime;


}
