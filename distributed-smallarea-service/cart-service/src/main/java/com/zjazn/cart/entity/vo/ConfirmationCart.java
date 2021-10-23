package com.zjazn.cart.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationCart {

    @ApiModelProperty(value = "购物车id")
    private String id;

    @ApiModelProperty(value = "推动购物车的用户")
    private String userId;

    @ApiModelProperty(value = "哪家店的购物车")
    private String storeId;

    @ApiModelProperty(value = "购物车中的商品详情")
    private List<ConfirmationItem> confirmationItems;
}
