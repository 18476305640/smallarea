package com.zjazn.cart.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationItem {
    @ApiModelProperty(value = "购物车内商品记录")
    private String id;

    @ApiModelProperty(value = "属于哪个购物车")
    private String cartId;

    @ApiModelProperty(value = "商品id（平衡字段）")
    private String goodsId;

    @ApiModelProperty(value = "商品的型号")
    private String goodsStyleId;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "数量")
    private Integer count;

    @ApiModelProperty(value = "商品style封面")
    private String cover;

    @ApiModelProperty(value = "样式名称")
    private String name;

    @ApiModelProperty(value = "库存")
    private String stock;


}
