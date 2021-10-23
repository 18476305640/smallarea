package com.zjazn.common.common;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationToUserDto {
    public static UserDto getUserDto() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return JSON.parseObject((String) authentication.getPrincipal(), UserDto.class);
    }


}
