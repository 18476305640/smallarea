package com.zjazn.cart.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsToCart {
    private String goods_id;
    private String goods_style_id;
    private Integer count;
}
