package com.zjazn.product.service;

import com.zjazn.product.entity.vo.goods_star;
import com.zjazn.product.service.impl.StoreStarServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
@FeignClient(name="store-service", fallback= StoreStarServiceImpl.class)
public interface StoreStarService {
    @PostMapping("/store/getStarByGoodsIdList")
    public List<goods_star> getStarByGoodsIdList(@RequestBody String[] goods_ids);

    @GetMapping("/store/getGoodsPraisePercentage")
    public Float getGoodsPraisePercentage(@RequestParam("goods_id") String goods_id);

    @GetMapping("/store/getCommentByGoodsId")
    public Integer getCommentByGoodsId(@RequestParam("goods_id") String goods_id);
    @PostMapping("/store/rmStore")
    public int rmStore(@RequestParam("store_id")  String store_id);
}
