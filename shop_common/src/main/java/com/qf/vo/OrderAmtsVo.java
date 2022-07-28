package com.qf.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderAmtsVo implements Serializable {

	private static final long serialVersionUID = 3840196229938738818L;

	private String[] dayData;//日期数据
	
	private BigDecimal[] orderAmtData;//日订单金额

	private Integer[] orderCntData;//日订单量


	
}
