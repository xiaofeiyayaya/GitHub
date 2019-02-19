package com.cn.jxf.test.time;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeDemo1 {

	
	//@Scheduled(cron = "0 0/1 9-17 * * ?")
	public void demo3(){
		System.out.println("33333"+"----"+"id"+Thread.currentThread().getId()+"---------name"+Thread.currentThread().getName()+"----"+System.currentTimeMillis());
		try {
			Thread.sleep(2000*60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Scheduled(cron = "0 0/1 9-17 * * ?")
	public void demo4(){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("444444444"+"----"+"id"+Thread.currentThread().getId()+"---------name"+Thread.currentThread().getName()+"----"+System.currentTimeMillis());
	}
}
