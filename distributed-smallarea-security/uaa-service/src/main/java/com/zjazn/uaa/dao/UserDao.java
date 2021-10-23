package com.zjazn.uaa.dao;

import com.zjazn.uaa.model.PermissionDto;
import com.zjazn.uaa.model.User;
import com.zjazn.uaa.model.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    UserDto getUserByname(@Param("username") String username);
    List<PermissionDto> getPermissionById(@Param("id") String id);

    //这个方法是根据用户opid获取对应用户的信息
    User getUserByWxOpid(@Param("wx_opid") String opid);

    int createUser(User user);

    int countUser();
}
