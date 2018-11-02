package com.cn.jxf.mapper.system;

import java.util.List;
import java.util.Map;

import com.cn.jxf.domain.system.UserRole;


public interface UserRoleMapper {
	
	List<UserRole> searchUserRole(Map<String, Object> map);
}
