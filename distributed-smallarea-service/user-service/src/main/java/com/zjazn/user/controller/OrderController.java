package com.zjazn.user.controller;

import com.zjazn.common.common.AuthenticationToUserDto;
import com.zjazn.common.common.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {
    @GetMapping(value = "/test")
    @PreAuthorize("hasAuthority('base')")//拥有p1权限方可访问此url
    public String r1(){
        UserDto userDto = AuthenticationToUserDto.getUserDto();
        log.info("********************DATA:："+userDto.getUsername());
        return userDto.getUsername()+"正在测试~";
    }

}
