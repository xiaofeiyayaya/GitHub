package com.cn.jxf.test.time;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeDemo2 {
	
	//@Scheduled(cron = "0 0/1 9-17 * * ?")
	public void demo1(){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1111111"+"----"+"id"+Thread.currentThread().getId()+"---------name"+Thread.currentThread().getName()+"----"+System.currentTimeMillis());
	}
	
	//@Scheduled(cron = "0 0/1 9-17 * * ?")
	public void demo2(){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("222222"+"----"+"id"+Thread.currentThread().getId()+"---------name"+Thread.currentThread().getName()+"----"+System.currentTimeMillis());
	}

}
