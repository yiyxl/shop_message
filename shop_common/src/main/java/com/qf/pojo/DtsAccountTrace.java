package com.qf.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
* <p>
    * 账户流水表
    * </p>
*
* @author zhaojian
* @since 2021-05-12
*/
    @Data
    @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class DtsAccountTrace implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
    * 操作流水
    */
    private String traceSn;

    /**
    * 用户表的用户ID
    */
    private Integer userId;

    /**
    * 操作类型 0:系统结算 1:用户提现
    */
    private Integer type;

    /**
    * 操作金额
    */
    private BigDecimal amount;

    /**
    * 总申请金额
    */
    private BigDecimal totalAmount;

    /**
    * 申请时间
    */
    private Date addTime;

    /**
    * 手机号
    */
    private String mobile;

    /**
    * 短信提取码
    */
    private String smsCode;

    /**
    * 审批状态
    */
    private Integer status;

    /**
    * 消息内容
    */
    private String traceMsg;

    /**
    * 审批时间
    */
    private Date updateTime;


}
