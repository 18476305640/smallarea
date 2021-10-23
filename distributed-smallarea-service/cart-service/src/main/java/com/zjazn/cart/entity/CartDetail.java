package com.zjazn.cart.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2021-07-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CartDetail对象", description="")
public class CartDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "购物车内商品记录")
    private String id;

    @ApiModelProperty(value = "属于哪个购物车")
    private String cartId;

    @ApiModelProperty(value = "商品id（平衡字段）")
    private String goodsId;

    @ApiModelProperty(value = "商品的型号")
    private String goodsStyleId;

    @ApiModelProperty(value = "商品style封面")
    private String cover;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "创建的时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新的时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
