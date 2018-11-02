package com.cn.jxf.test.thread;


public class ThreadDemo implements Runnable {

	private String name;

	public ThreadDemo(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			for (long k = 0; k < 100000000; k++)
				;
			System.out.println(name + ":" + i);
		}

	}

	public static void main(String[] args) {
		ThreadDemo thread1 = new ThreadDemo("鸣人");
		ThreadDemo thread2 = new ThreadDemo("佐助");
		Thread t1 = new Thread(thread1);
		Thread t2 = new Thread(thread2);
		Thread t3 = new Thread();
		t1.start();
		try {
			t2.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
	}

}
