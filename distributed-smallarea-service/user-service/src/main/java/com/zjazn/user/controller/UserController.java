package com.zjazn.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjazn.common.baseUtils.DateFormatUtils;
import com.zjazn.user.entity.User;
import com.zjazn.user.entity.fo.UserFo;
import com.zjazn.user.service.UserService;
import com.zjazn.common.conduit.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-17
 */
@RestController
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 根据用户id查询用户信息
     * @return
     */
    @GetMapping("/getUserById")
    public R getUserById(String id) {
        User user = userService.getById(id);
        if (user == null) {
            return R.error().data("user",user).message("没有查询到结果");
        }
        return R.ok().data("user",user);
    }
    /**
     * 根据用户名查询用户信息
     */
    @GetMapping("/getUserByusername")
    public R getUserByusername(String username) {
        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(User::getUsername,username);
        User user = userService.getOne(userWrapper);
        if (user == null) {
            return R.error().data("user",user).message("没有查询到结果");
        }
        return R.ok().data("user",user);
    }
    /**
     * 根据用户任何信息查询符合的用户记录
     */
    @GetMapping("/getUserByuser")
    public R getUserByuser(UserFo userFo) {

        LambdaQueryWrapper<User> userWrapper = new LambdaQueryWrapper<>();

        if(userFo.getId() != null) {
            userWrapper.eq(User::getId,userFo.getId());
        }
        if(userFo.getUsername() != null) {
            userWrapper.like(User::getUsername,userFo.getUsername());
        }
        if(userFo.getEmail() != null) {
            userWrapper.like(User::getEmail,userFo.getEmail());
        }
        if(userFo.getFullname() != null) {
            userWrapper.like(User::getFullname,userFo.getFullname());
        }
        if (userFo.getStart_birth() != null) {
            userWrapper.apply("DATE(birth) >= STR_TO_DATE('"+ DateFormatUtils.DataToString(userFo.getStart_birth(),"yyyy-MM-dd")+" 00:00:00','%Y-%m-%d %H:%i:%s')");

        }
        if (userFo.getEnd_birth() != null) {
            userWrapper.apply("DATE(birth) <= STR_TO_DATE('1999-07-21 23:59:59','%Y-%m-%d %H:%i:%s')");
        }
        if (userFo.getStart_create_time() != null) {
            userWrapper.apply("DATE(create_time) >= STR_TO_DATE('"+DateFormatUtils.DataToString(userFo.getStart_create_time(),"yyyy-MM-dd")+" 00:00:00','%Y-%m-%d %H:%i:%s')");

        }
        if (userFo.getEnd_create_time() != null) {
            userWrapper.apply("DATE(create_time) <= STR_TO_DATE('"+DateFormatUtils.DataToString(userFo.getEnd_create_time(),"yyyy-MM-dd")+" 23:59:59','%Y-%m-%d %H:%i:%s')");
        }

        List<Object> users = userService.listObjs(userWrapper);

        return R.ok().data("users",users);
    }



    /**
     * 根据用户id修改用户信息
     */
    @PostMapping("/updateUser")
    public R updateUserById(@RequestBody User user) {
        log.info("*****************要更新的用户数据："+user);
        boolean b = userService.updateById(user);
        if (b) {
            return R.ok().message("用户信息更新完成!");
        }
        return R.error().message("用户信息更新失败！");
    }


}

