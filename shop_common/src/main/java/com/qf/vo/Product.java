package com.qf.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
	private String[] specifications;
	private BigDecimal price;
	private Integer number;
	private String url;


}
