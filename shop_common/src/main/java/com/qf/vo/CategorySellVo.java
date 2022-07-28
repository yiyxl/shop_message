package com.qf.vo;



import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategorySellVo implements Serializable {

    private static final long serialVersionUID = 96458407347975166L;

    private String[] categoryNames;//一级大类目录名称

    private List<CategorySellAmts> categorySellData;//大类销售金额集合


}
