package com.zjazn.store.controller;


import com.zjazn.store.entity.vo.goods_star;
import com.zjazn.store.mapper.StarMapper;
import com.zjazn.store.service.StarService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-06-28
 */
@RestController
public class StarController {
    @Resource
    private StarService starService;

    @Resource
    private StarMapper starMapper;


    /*根据goods_id数组获取平均星数*/
    @PostMapping("/getStarByGoodsIdList")
    public List<goods_star> getStarByGoodsIdList(@RequestBody String[] goods_ids) {
        List<goods_star> goodsStar = starService.getGoodsStar(goods_ids);
        return goodsStar;
    }
    //根据goods_id获取商品的好评率
    @GetMapping("/getGoodsPraisePercentage")
    public Float getGoodsPraisePercentage(@RequestParam("goods_id") String goods_id) {
        Float goodsPraisePercentage = starMapper.getGoodsPraisePercentage(goods_id);
        return goodsPraisePercentage;
    }
    //根据goods_id查看有多少评论
    @GetMapping("/getCommentByGoodsId")
    public Integer getCommentByGoodsId(@RequestParam("goods_id") String goods_id) {
        Integer commentNumber = starMapper.getCommentByGoodsId(goods_id);
        return commentNumber;
    }








}

