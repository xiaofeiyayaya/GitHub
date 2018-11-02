package com.cn.jxf.util;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class ScheduledTask {
	
	
	//@Scheduled(cron = "0 0/1 * * * ?")
	public void task(){
		System.out.println(new Date());
	}
}
