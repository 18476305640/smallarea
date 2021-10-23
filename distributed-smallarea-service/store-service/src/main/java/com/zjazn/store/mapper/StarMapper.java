package com.zjazn.store.mapper;

import com.zjazn.store.entity.Star;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjazn.store.entity.vo.goods_star;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-06-28
 */
public interface StarMapper extends BaseMapper<Star> {
    List<goods_star> getAvgStarByGoods(@Param("goods_ids") String[] goods_ids);

    Float getGoodsPraisePercentage(@Param("goods_id") String goods_id);
    //根据商品id查看有多少条评论
    Integer getCommentByGoodsId(@Param("goods_id") String goods_id);

}
