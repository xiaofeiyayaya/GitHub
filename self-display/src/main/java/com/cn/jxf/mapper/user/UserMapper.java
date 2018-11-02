package com.cn.jxf.mapper.user;


import com.cn.jxf.domain.user.User;

public interface UserMapper {
    
    User findUserByCode(String userCode);
    
    User userLogin(User user);
    
    int insert(User user);
}