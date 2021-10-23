package com.zjazn.store.entity;

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
 * @since 2021-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Store对象", description="")
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商店编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
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
