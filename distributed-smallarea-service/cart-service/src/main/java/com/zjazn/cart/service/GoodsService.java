package com.zjazn.cart.service;

import com.zjazn.cart.entity.vo.Goods;
import com.zjazn.cart.entity.vo.GoodsStyle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Component
@FeignClient(name = "product-service")
public interface GoodsService {
    @GetMapping("/product/getGoodsPrice")
    public BigDecimal getGoodsPrice(@RequestParam("goods_style_id") String goods_style_id);


    @GetMapping("/product/getGoodsId")
    public String getGoodsId(@RequestParam("goods_style_id") String goods_style_id);

    @GetMapping("/product/getGoodsByGoodsId")
    public Goods getGoodsByGoodsId(@RequestParam("goods_id") String goods_id);


    @PostMapping("/product/getGoodsStyleList")
    public List<GoodsStyle> getGoodsStyleList(List<String> style_ids);

    @GetMapping("/product/getGoodsStyleOneById")
    public GoodsStyle getGoodsStyleOneById(@RequestParam("style_id") String style_id );





}
