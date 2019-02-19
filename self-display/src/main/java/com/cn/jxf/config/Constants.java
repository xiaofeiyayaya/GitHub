package com.cn.jxf.config;

import org.apache.commons.lang3.RandomStringUtils;

public class Constants {
	public static final int KEY_LENGTH = 18;
	public static final int FLAG_0 = 0;// 未提交
	public static final int FLAG_1 = 1;// 正常
	public static final int FLAG_10 = 10;// 删除

	public static final String PAGE_SIZE = "30";

	public static final String PAGE_START = "start";
	public static final String PAGE_END = "end";

	public static String getId() {
		return RandomStringUtils.randomAlphanumeric(KEY_LENGTH);
	}

}