package com.zjazn.cart.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2021-06-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store  {

    @ApiModelProperty(value = "商店编号")
    private String id;

    @ApiModelProperty(value = "所属用户编号")
    private String userId;

    @ApiModelProperty(value = "全局商店分类编号")
    private String globalTypeId;

    @ApiModelProperty(value = "店名")
    private String name;

    @ApiModelProperty(value = "主张")
    private String introduce;

    @ApiModelProperty(value = "封面")
    private String cover;

    @ApiModelProperty(value = "店公告信息")
    private String mession;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "建店时间")
    private Date createTime;

    @ApiModelProperty(value = "店信息修改时间")
    private Date updateTime;


}
