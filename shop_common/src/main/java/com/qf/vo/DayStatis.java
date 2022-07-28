package com.qf.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 按天统计
 */
@Data
public class DayStatis {

	private String dayStr;

	private int cnts;

	private BigDecimal amts;
	

}
