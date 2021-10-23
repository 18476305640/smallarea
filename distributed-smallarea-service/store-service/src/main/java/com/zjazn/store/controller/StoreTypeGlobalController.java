package com.zjazn.store.controller;


import com.zjazn.store.service.StoreTypeGlobalService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/smallarea/store-type-global")
public class StoreTypeGlobalController {
    @Resource
    private StoreTypeGlobalService storeTypeGlobalService;


}

