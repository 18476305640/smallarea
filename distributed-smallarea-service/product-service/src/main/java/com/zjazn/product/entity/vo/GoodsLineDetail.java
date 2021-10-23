package com.zjazn.product.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GoodsLineDetail {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "所属的商店编号")
    private String storeId;

    @ApiModelProperty(value = "所属商品类型编号")
    private String partitionGlobalId;

    @ApiModelProperty(value = "所属店内分类")
    private String partitionLocalId;

    @ApiModelProperty(value = "商品名")
    private String name;

    @ApiModelProperty(value = "商品名的描述")
    private String describe;

    @ApiModelProperty(value = "商品的封面")
    private String cover;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "轮播图json")
    private String carousel;

    @ApiModelProperty(value = "参数json")
    private String params;

    @ApiModelProperty(value = "对商品的图文介绍")
    private String more;

    private Date createTime;

    private Date updateTime;

}
