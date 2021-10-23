package com.zjazn.uaa.dao;

import com.zjazn.uaa.model.UserLineRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLineRoleDao {
    int createLine(UserLineRole userLineRole);
}
