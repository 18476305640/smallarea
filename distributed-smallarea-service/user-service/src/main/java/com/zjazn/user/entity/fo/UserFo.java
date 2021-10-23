package com.zjazn.user.entity.fo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 这是用于用户条件查询对象，即用于多条件查询
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFo {
    private String id;
    private String username;
    private String fullname;
    private String email;
    private Date start_birth;
    private Date end_birth;
    private Date start_create_time;
    private Date end_create_time;


}
