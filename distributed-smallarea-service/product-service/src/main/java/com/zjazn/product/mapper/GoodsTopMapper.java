package com.zjazn.product.mapper;

import com.zjazn.product.entity.GoodsTop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-06-29
 */
public interface GoodsTopMapper extends BaseMapper<GoodsTop> {
    Integer getGoodsFollowNumber(@Param("goods_id") String goods_id);
}
