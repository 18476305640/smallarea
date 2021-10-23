package com.zjazn.product.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-06-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsDetail对象", description="")
public class GoodsDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "产品详情编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "商品编号")
    private String goodsId;

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

    @ApiModelProperty(value = "产品详情创建时间")
    private Date createTime;

    @ApiModelProperty(value = "产品详情更新时间")
    private Date updateTime;


}
