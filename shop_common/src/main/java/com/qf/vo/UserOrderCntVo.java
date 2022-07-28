package com.qf.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 指定日期用户数和订单数Vo
 */
@Data
public class UserOrderCntVo implements Serializable {

	private static final long serialVersionUID = -5460904409450124808L;

	//日期数据
	private String[] dayData;

	//每日用户新增量
	private Integer[] userCnt;

	//每日订单量
	private Integer[] orderCnt;


}
