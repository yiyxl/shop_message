package com.qf.vo;


import com.qf.pojo.DtsBrand;
import lombok.Data;

import java.io.Serializable;

@Data
public class DtsBrandVo extends DtsBrand implements Serializable{

	private static final long serialVersionUID = 6530090986580196500L;
	
	private Integer[] categoryIds;


	
	

}
