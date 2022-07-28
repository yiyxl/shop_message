package com.qf.util;

import com.qf.annotation.RequiresPermissionsDesc;
import lombok.Data;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * 权限自定义封装实体类
 * 这里面的属性全都是annotation注解对象
 */
@Data
public class Permission {

	//权限字符串注解
	private RequiresPermissions requiresPermissions;

	//权限描述字符串注解
	private RequiresPermissionsDesc requiresPermissionsDesc;

	//controller方法上的路径, 例如: POST /admin/admin/delete
	private String api;


}
