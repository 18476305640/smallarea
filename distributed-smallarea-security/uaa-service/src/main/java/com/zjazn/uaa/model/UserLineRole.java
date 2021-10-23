package com.zjazn.uaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLineRole {
    private String user_id;
    private String role_id;
    private Date create_time;
    private String creator;
}
