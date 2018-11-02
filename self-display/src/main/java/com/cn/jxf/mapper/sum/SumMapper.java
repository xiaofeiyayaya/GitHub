package com.cn.jxf.mapper.sum;

import com.cn.jxf.domain.sum.Sum;

public interface SumMapper {
	
	void add(Sum sum);
	
	void update(Sum sum);
	
	Sum select(Integer id);
}
