package com.qf.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解, 请求权限描述
 * @Target : 定注解的作用目标, 也就是自定义的这个注解在哪里使用
 * ElementType.TYPE: 接口、类、枚举、注解
 * ElementType.METHOD : 方法
 *
 * @Retention 指定注解的保留位置
 * RetentionPolicy.RUNTIME : 这种类型的Annotations将被JVM保留,
 * 							  所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPermissionsDesc {

	/**
	 * 菜单名字
	 * @return
	 */
	String[] menu();


	/**
	 * 按钮名字
	 * @return
	 */
	String button();
}
