package com.qf.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 品牌商表
 * </p>
 *
 * @author zhaojian
 * @since 2021-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DtsBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 品牌商名称
     */
    private String name;

    /**
     * 品牌商简介
     */
    @TableField("`desc`")
    private String desc;

    /**
     * 品牌商页的品牌商图片
     */
    private String picUrl;

    private Integer sortOrder;

    /**
     * 品牌商的商品低价，仅用于页面展示
     */
    private BigDecimal floorPrice;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 分享二维码图片
     */
    private String shareUrl;

    /**
     * 管理用户id
     */
    private Integer adminId;

    /**
     * 逻辑删除
     */
    private Boolean deleted;

    private String commpany;

    /**
     * 自动监控更新商品
     */
    private Boolean autoUpdateGood;

    /**
     * 店铺url地址
     */
    private String shopUrl;

    /**
     * 默认的商品类别id
     */
    private Integer defaultCategoryId;

    /**
     * 默认商品页面数
     */
    private Integer defaultPages;

    /**
     * 店铺商品溢价
     */
    private Integer addPrecent;

    /**
     * 提货地址
     */
    private String address;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * 提货时间配置
     */
    private String fetchTimeRules;


}
