package com.qf.vo;

import lombok.Data;

import java.util.List;

/**
 * 用于返回给前端的权限表现层实体类
 */
@Data
public class PermVo {

	/**
	 * 权限字符串, 如果是模块, id的值为: 系统管理或者角色管理等
	 * 如果是具体功能权限, 值为: admin:admin:update
	 */
	private String id;

	//权限具有的功能描述
	private String label;

	//值例如: POST /admin/admin/update
	private String api;

	//这个功能权限的子功能权限
	private List<PermVo> children;

}
