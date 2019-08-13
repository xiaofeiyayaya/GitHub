package com.cn.jxf.mapper.system;

import java.util.List;
import java.util.Map;

import com.cn.jxf.domain.system.UserRole;
import com.cn.jxf.mapper.BaseMapper;

public interface UserRoleMapper extends BaseMapper<UserRole, String> {

	List<UserRole> searchUserRole(Map<String, Object> map);

	int deleteUserRoleByMap(Map<String, Object> map);
}
