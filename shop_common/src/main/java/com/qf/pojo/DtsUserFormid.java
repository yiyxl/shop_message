package com.qf.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class DtsUserFormid implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 缓存的FormId
     */
    @TableField("formId")
    private String formId;

    /**
     * 是FormId还是prepayId
     */
    private Boolean isprepay;

    /**
     * 可用次数，fromId为1，prepay为3，用1次减1
     */
    @TableField("useAmount")
    private Integer useAmount;

    /**
     * 过期时间，腾讯规定为7天
     */
    private Date expireTime;

    /**
     * 微信登录openid
     */
    @TableField("openId")
    private String openId;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除
     */
    private Boolean deleted;


}
