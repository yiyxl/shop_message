package com.qf.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.qf.handler.JsonStringArrayTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 退款跟踪表
 * </p>
 *
 * @author zhaojian
 * @since 2021-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DtsRefundTrace implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单表的订单ID
     */
    private Integer orderId;

    /**
     * 订单编号
     */
    private String orderSn;

    /**
     * 审批状态：0 退款申请 1 退款审批通过 2 退款审批拒绝 3 退款完成
     */
    private Integer status;

    /**
     * 退款原因
     */
    private String refundReason;

    /**
     * 是否含有图片
     */
    private Boolean hasPicture;

    /**
     * 退款质量图片地址列表 采用JSON数组格式
     */
    @TableField(typeHandler = JsonStringArrayTypeHandler.class)
    private String[] picUrls;

    /**
     * 创建时间,即退款申请时间
     */
    private Date addTime;

    /**
     * 审批内容
     */
    private String approveMsg;

    /**
     * 审批时间
     */
    private Date approveTime;

    /**
     * 退款物流内容
     */
    private String freightMsg;

    /**
     * 退款时间
     */
    private Date refundTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Boolean deleted;


}
