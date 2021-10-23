package com.zjazn.cart.entity.vo;

import com.zjazn.cart.entity.CartDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadCart{
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "购物车id")
    private String id;

    @ApiModelProperty(value = "推动购物车的用户")
    private String userId;

    @ApiModelProperty(value = "哪家店的购物车")
    private String storeId;

    @ApiModelProperty(value = "购物车中的商品")
    private List<CartDetail> cartDetailList;




}
