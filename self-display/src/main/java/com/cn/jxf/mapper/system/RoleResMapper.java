package com.cn.jxf.mapper.system;

import java.util.List;
import java.util.Map;

import com.cn.jxf.domain.system.RoleRes;
import com.cn.jxf.mapper.BaseMapper;


public interface RoleResMapper extends BaseMapper<RoleRes, String> {

	int insertBatch(List<RoleRes> list);

	int deleteRoleResByMap(Map<String, Object> map);

	List<RoleRes> searchRoleRes(Map<String, Object> map);
}
