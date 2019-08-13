package com.cn.jxf.mapper.system;

import java.util.List;

import com.cn.jxf.domain.system.Role;
import com.cn.jxf.mapper.BaseMapper;

public interface RoleMapper extends BaseMapper<Role, String> {
	
	List<Role> searchAllRole();
	
}
