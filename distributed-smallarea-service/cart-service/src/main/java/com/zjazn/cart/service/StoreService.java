package com.zjazn.cart.service;

import com.zjazn.cart.entity.vo.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "store-service")
public interface StoreService {

    @GetMapping("/store/getStoreById")
    public Store getStoreById(@RequestParam("store_id") String store_id);

    @GetMapping("/store/getGoodsPraisePercentage")
    public Float getGoodsPraisePercentage(@RequestParam("goods_id") String goods_id);


    @GetMapping("/store/getStorePraisePercentage")
    public Float getStorePraisePercentage(@RequestParam("store_id") String store_id);


}
