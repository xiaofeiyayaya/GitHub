package com.cn.jxf.mapper.user;


import java.util.List;

import com.cn.jxf.domain.user.User;
import com.cn.jxf.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User, String>{
    
    User findUserByCode(String userCode);
    
    User userLogin(User user);
    
    int insert(User user);
    
    List<User> searchAllUser();
}