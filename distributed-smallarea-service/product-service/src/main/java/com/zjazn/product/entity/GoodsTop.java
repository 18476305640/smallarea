package com.zjazn.product.entity;

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
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GoodsTop对象", description="")
public class GoodsTop implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关注记录id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "哪个用户id关注该商品")
    private String userId;

    @ApiModelProperty(value = "关注的商品id")
    private String goodsId;

    @ApiModelProperty(value = "关注时间")
    private Date createTime;

    @ApiModelProperty(value = "关注更新")
    private Date updateTime;


}
