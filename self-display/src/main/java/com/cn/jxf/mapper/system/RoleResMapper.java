package com.cn.jxf.mapper.system;

import java.util.List;
import java.util.Map;

import com.cn.jxf.domain.system.Res;


public interface RoleResMapper {
	
	List<Res> searchResId(Map<String, Object> map);
}
