package com.cn.jxf.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cn.jxf.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ThreadDemo implements Runnable{
	
	private String name;
	public ThreadDemo(String name){
		this.name = name;
	}
	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {  
	           for(long k=0;k<100000000;k++);  
	           System.out.println(name+":"+i);  
	       }       
		
	}

	@Test
	public void DemoMain(){
		ThreadDemo thread1 = new ThreadDemo("小飞1");
		ThreadDemo thread2 = new ThreadDemo("小飞2");
		Thread t1 = new Thread(thread1);
		Thread t2 = new Thread(thread2); 
		t1.start();
		t2.start();
		
	}
}
