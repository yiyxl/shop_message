package com.qf.util;

import java.util.Random;

/**
 * 获取随机数和随机字符串工具类
 * @author zhaojian
 */
public class CharUtil {

    /**
     * 获取随机字符串
     * @param num 需要生成的字符串个数
     * @return
     */
	public static String getRandomString(Integer num) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

    /**
     * 生成随机数
     * @param num 生成随机数的个数
     * @return
     */
	public static String getRandomNum(Integer num) {
		String base = "0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

}
