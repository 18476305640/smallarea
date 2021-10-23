package com.zjazn.cart.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {


    @ApiModelProperty(value = "商品编号")
    private String id;

    @ApiModelProperty(value = "所属的商店编号")
    private String storeId;

    @ApiModelProperty(value = "所属商品类型编号")
    private String partitionGlobalId;

    @ApiModelProperty(value = "所属店内分类")
    private String partitionLocalId;

    private Date createTime;

    private Date updateTime;


}
