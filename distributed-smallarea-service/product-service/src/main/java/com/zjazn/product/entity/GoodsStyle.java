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
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsStyle对象", description="")
public class GoodsStyle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品的规格id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "商品id")
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
