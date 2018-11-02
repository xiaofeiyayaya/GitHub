package com.cn.jxf.mapper.system;

import java.util.List;
import java.util.Map;

import com.cn.jxf.domain.system.Res;


public interface ResourceMapper{

	List<Res> searchAllResource(Map<String,Object> map);
	
	List<Res> searchResource(Map<String,Object> map);
}
