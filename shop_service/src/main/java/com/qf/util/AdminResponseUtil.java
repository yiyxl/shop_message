package com.qf.util;

/**
 * 管理后台接口枚举信息的响应
 * 
 * @author zhaojian
 */
public class AdminResponseUtil extends ResponseUtil {

	/**
	 * 按枚举返回错误响应结果
	 * 
	 * @return
	 */
	public static Object fail(AdminResponseCode responseCode) {
		return fail(responseCode.code(), responseCode.desc());
	}
}
