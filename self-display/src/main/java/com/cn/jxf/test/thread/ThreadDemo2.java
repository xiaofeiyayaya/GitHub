package com.cn.jxf.test.thread;

public class ThreadDemo2 implements Runnable {

	private Foo foo = new Foo();

	public int fix(int y) {
		return foo.fix(y);
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			synchronized (this) {

				this.fix(30);
				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "当前foo对象的x值:" + foo.getX());
			}
		}
	}

	public static void main(String[] args) {
		ThreadDemo2 td2 = new ThreadDemo2();
		Thread ta = new Thread(td2, "threadA");
		Thread tb = new Thread(td2, "threadB");
		ta.start();
		tb.start();
	}

}
