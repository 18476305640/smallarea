package com.zjazn.product.service.impl;


import com.zjazn.product.entity.vo.goods_star;
import com.zjazn.product.service.StoreStarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@Slf4j
@Component
public class StoreStarServiceImpl implements StoreStarService {


    @Override
    public List<goods_star> getStarByGoodsIdList(String[] goods_ids) {
        log.info("获取一些指定goods_id的平均评论失败！");
        return null;
    }

    @Override
    public Float getGoodsPraisePercentage(String goods_id) {
        log.info("获取好评率失败Callback!!");
        return null;
    }

    @Override
    public Integer getCommentByGoodsId(String goods_id) {
        log.info("获取好评论条数失败Callback!!");
        return null;
    }

    @Override
    public int rmStore(String store_id) {
        log.info("调用目标失败Callback!!");
        return 0;
    }
}
