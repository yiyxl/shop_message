package com.qf.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 不同分类卖出统计
 */
@Data
public class CategorySellAmts implements Serializable{

	private static final long serialVersionUID = 677901688504280013L;

	private String name;
	
	private BigDecimal value;


}
