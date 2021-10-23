package com.zjazn.product.service;

import com.zjazn.product.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-22
 */
public interface GoodsService extends IService<Goods> {
    //根据全局分类id获取分类下的商品数据  分页
//    List<goods> getGoodsByGlobalTypeId(String id,Integer item_number,Integer page_number);



    //根据商品id获取显示的信息
    public Map<String, Object> getGoodsDetail(String goods_id);
}
