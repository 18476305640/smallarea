package com.zjazn.product.mapper;

import com.zjazn.product.entity.vo.GoodsLineDetail;
import com.zjazn.product.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjazn.product.entity.vo.goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-06-22
 */


public interface GoodsMapper extends BaseMapper<Goods> {
    List<goods> getGoodsByGlobalTypeId(@Param("id") String id, @Param("search") String serchar , @Param("start_number") Integer start_number , @Param("item_number") Integer item_number );

    //ps: GoodsLineDetail 连接查询
    GoodsLineDetail GoodsLineDetail(@Param("goods_id") String goods_id);
}
