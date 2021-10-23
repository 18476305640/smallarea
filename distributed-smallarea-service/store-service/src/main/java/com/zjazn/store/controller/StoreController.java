package com.zjazn.store.controller;


import com.zjazn.store.entity.Store;
import com.zjazn.store.mapper.StoreMapper;
import com.zjazn.store.service.StoreService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-28
 */
@RestController
public class StoreController {
    @Resource
    private StoreService storeService;

    @Resource
    private StoreMapper storeMapper;

    //生产者：根据store_id查询商家信息
    @GetMapping("/getStoreById")
    public Store getStoreById(@RequestParam("store_id") String store_id) {
        Store store = storeService.getById(store_id);
        return  store;
    }

    //生产者：查询商店的好评率
    @GetMapping("/getStorePraisePercentage")
    public Float getStorePraisePercentage(@RequestParam("store_id") String store_id) {
        Float s = storeMapper.getStorePraisePercentage(store_id);
        return s;
    }
}

