package com.zjazn.cart.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2021-07-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsStyle {

    @ApiModelProperty(value = "商品的规格id")
    private String id;

    @ApiModelProperty(value = "商品")
    private String goodsId;

    @ApiModelProperty(value = "型号名")
    private String name;

    @ApiModelProperty(value = "该型号的价格")
    private BigDecimal price;

    @ApiModelProperty(value = "该型号的封面")
    private String cover;

    @ApiModelProperty(value = "该型号的库存")
    private Integer stock;

    @ApiModelProperty(value = "创建的时间")
    private Date createTime;

    @ApiModelProperty(value = "更新的时间")
    private Date updateTime;


}
