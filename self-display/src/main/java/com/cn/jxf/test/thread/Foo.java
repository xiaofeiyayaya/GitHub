package com.cn.jxf.test.thread;

public class Foo {
	
	private int x = 100;
	
	public int getX(){
		return x;
	}
	
	public int fix(int y){
		x = x -y;
		return x;
	}
	
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
}
