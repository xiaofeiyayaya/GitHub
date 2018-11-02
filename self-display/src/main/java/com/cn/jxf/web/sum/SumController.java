package com.cn.jxf.web.sum;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.jxf.domain.sum.Sum;
import com.cn.jxf.mapper.sum.SumMapper;

import jdk.nashorn.internal.ir.annotations.Reference;

@Controller
@RequestMapping("/sum")
public class SumController {
	
	@Resource
	private SumMapper sumMapper;
	
	
	@RequestMapping("/add")
	public void add(){
		Sum sum = new Sum();
		sum.setName("小飞");
		sum.setSum(100);
		sumMapper.add(sum);
		System.out.println("ccccccc");
	}
	
	@Transactional
	@RequestMapping("/update")
	public void update(){
		Sum sum = sumMapper.select(1);
		sum.setSum(sum.getSum()-10);
		sumMapper.update(sum);
		System.out.println(sum.getSum());
		
		
		Sum sum2 = update2();
		System.out.println(sum2.getSum());
	}
	
	
	public Sum update2(){
		Sum sum = sumMapper.select(1);
		sum.setSum(sum.getSum()-10);
		sumMapper.update(sum);
		return sum;
		
	}
}
