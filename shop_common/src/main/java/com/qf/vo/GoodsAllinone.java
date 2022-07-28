package com.qf.vo;

import com.qf.pojo.DtsGoods;
import com.qf.pojo.DtsGoodsAttribute;
import com.qf.pojo.DtsGoodsProduct;
import com.qf.pojo.DtsGoodsSpecification;
import lombok.Data;

@Data
public class GoodsAllinone {

	private DtsGoods goods;
	private DtsGoodsSpecification[] specifications;
	private DtsGoodsAttribute[] attributes;
	// 这里采用 Product 再转换到 DtsGoodsProduct
	private DtsGoodsProduct[] products;



}
