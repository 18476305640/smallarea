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
@ApiModel(value="StoreTypeGlobal对象", description="")
public class StoreTypeGlobal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "全局商店类型编号")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "全局商店类型名称")
    private String name;

    @ApiModelProperty(value = "全局商店类型封面")
    private String cover;

    @ApiModelProperty(value = "创建全局商店类型时间")
    private Date createTime;

    @ApiModelProperty(value = "修改全局商店类型时间")
    private Date updateTime;


}
