package com.zjazn.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjazn.common.conduit.R;
import com.zjazn.product.entity.GoodsStyle;
import com.zjazn.product.service.GoodsStyleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-03
 */
@RestController
@Slf4j
public class GoodsStyleController {
    @Resource
    private GoodsStyleService goodsStyleService;



    @GetMapping("/getGoodsStyle")
    @ApiOperation("根据goods_id请求goods_style信息")
    public R getGoodsStyle(@RequestParam("goods_id") String goods_id ) {
        QueryWrapper<GoodsStyle> qw = new QueryWrapper<>();
        qw.eq("goods_id", goods_id);
        List<GoodsStyle> goodsStyles = goodsStyleService.list(qw);

        //List<GoodsStyle>
        return R.ok().data("goodsStyles",goodsStyles);
    }

    @GetMapping("/getGoodsStyleById")
    @ApiOperation("根据goods_style_id获取style的信息")
    public R getGoodsStyleById(@RequestParam("goods_style_id") String goods_style_id ) {
        try {
            GoodsStyle goods_style = goodsStyleService.getById(goods_style_id);
            return R.ok().data("one_goods_style",goods_style);
        }catch (Exception e) {
            return R.error().message("获取goods_style失败");
        }
    }

    @PostMapping("/getGoodsStyleList")
    public List<GoodsStyle> getGoodsStyleList(@RequestBody List<String> style_ids) {
        Collection<GoodsStyle> goodsStyles = goodsStyleService.listByIds(style_ids);
        List<GoodsStyle> gs = new ArrayList<>();
        for (GoodsStyle goodsStyle : goodsStyles) {
            gs.add(goodsStyle);
        }

        log.info("***"+gs);
        return gs;
    }

    @GetMapping("/getGoodsStyleOneById")
    public GoodsStyle getGoodsStyleOneById(@RequestParam("style_id") String style_id ) {
        GoodsStyle goodsStyle = goodsStyleService.getById(style_id);
        return goodsStyle;
    }




}

