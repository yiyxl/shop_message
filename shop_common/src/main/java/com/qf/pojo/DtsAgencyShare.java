package com.qf.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhaojian
 * @since 2021-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DtsAgencyShare implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * url地址
     */
    private String shareUrl;

    /**
     * 分享对象类型 1：商品 2：品牌商铺 3：团购
     */
    private Integer type;

    /**
     * 分享对象id
     */
    private Integer shareObjId;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;


}
