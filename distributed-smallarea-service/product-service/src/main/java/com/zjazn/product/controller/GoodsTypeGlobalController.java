package com.zjazn.product.controller;


import com.zjazn.product.entity.vo.SmallTypeDesc;
import com.zjazn.product.entity.vo.TypeDesc;
import com.zjazn.common.conduit.R;
import com.zjazn.product.service.GoodsTypeGlobalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-22
 */
@RestController
@Slf4j
public class GoodsTypeGlobalController {
    @Resource
    private GoodsTypeGlobalService goodsTypeGlobalService;

    @GetMapping("/getGlobalType")
    public R getGlobalType() {
        List<TypeDesc> typeDescs = goodsTypeGlobalService.getTypeDescs();
        List<SmallTypeDesc> smallTypeDescs = goodsTypeGlobalService.getSmallTypeDescs();

        log.info("**********没有将二级分类封装一级分类的数据："+typeDescs);
        for (int i=0;  i < typeDescs.size(); i++) {
            ArrayList<SmallTypeDesc> children  = new ArrayList<>();
            for (int j=0; j < smallTypeDescs.size(); j++ ) {
                if(typeDescs.get(i).getId().equals(smallTypeDescs.get(j).getParentId())) {
                    children.add(smallTypeDescs.get(j));
                }
            }
            typeDescs.get(i).setChildren(children);
        }


        return R.ok().data("TypeDescs",typeDescs);
    }

}

