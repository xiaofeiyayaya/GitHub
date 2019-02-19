package com.cn.jxf.test.redis;

import redis.clients.jedis.Jedis;

public class RedisDemo {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		//jedis.auth("123"); // 密码认证
		System.out.println("Connection to server sucessfully");
		// 查看服务是否运行
		jedis.set("user", "namess");
		// System.out.println("Server is running: "+jedis.ping());
		System.out.println(jedis.get("user").toString());
		jedis.set("user", "name");
		System.out.println(jedis.get("user"));

	}
}
