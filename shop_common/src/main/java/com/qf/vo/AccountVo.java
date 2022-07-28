package com.qf.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountVo implements Serializable {

	private static final long serialVersionUID = 1567048369574496965L;

	private Integer userId;

	private BigDecimal remainAmount;

	private BigDecimal totalAmount;


}
