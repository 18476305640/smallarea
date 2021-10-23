package com.zjazn.product.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.zjazn.product.entity.Goods;
import com.zjazn.product.entity.GoodsStyle;
import com.zjazn.product.entity.vo.GoodsLineDetail;
import com.zjazn.product.entity.vo.goods_star;
import com.zjazn.product.mapper.GoodsMapper;
import com.zjazn.product.mapper.GoodsTopMapper;
import com.zjazn.product.service.GoodsStyleService;
import com.zjazn.product.service.GoodsTopService;
import com.zjazn.product.service.StoreStarService;
import com.zjazn.common.conduit.R;
import com.zjazn.product.entity.vo.goods;
import com.zjazn.product.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private StoreStarService storeStarService;

    @Resource
    private GoodsTopService goodsTopService;

    @Resource
    private GoodsTopMapper goodsTopMapper;

    @Resource
    private GoodsStyleService goodsStyleService;

    //API说明：根据分类id获取所属分类的商品，其中评分需要进行服务调用再通过循环将之加入商品数据中
    @GetMapping("/getGoodsByGlobalTypeIdLime")
    public R  getGoodsByGlobalTypeIdLime(String id,String search, Integer page_number, Integer item_number) {

        System.out.println("后端接收："+id+",item_number="+item_number+",page_number="+page_number);
        Integer start_number = (page_number-1)*item_number;
        List<goods> goods_arr = goodsMapper.getGoodsByGlobalTypeId(id,search, start_number, item_number);

        //将产品中的id列提取为字符数组
        String[] strings = new String[goods_arr.size()];
        for (int f=0; f<goods_arr.size(); f++) {
            strings[f] = goods_arr.get(f).getId();
        }
        List<goods_star> starsByGoods = storeStarService.getStarByGoodsIdList(strings);
        for (int i=0; i<goods_arr.size(); i++) {
            for (int j=0; j<starsByGoods.size(); j++) {
                if(goods_arr.get(i).getId().equals(starsByGoods.get(j).getGoods_id())) {
                    goods_arr.get(i).setStar(starsByGoods.get(j).getStar());
                }
            }
        }
        return R.ok().data("goods", goods_arr);
    }

    @GetMapping("/getGoodsDetailById")
    public R getGoodsDetailById(String goods_id) {
        Map<String, Object> goodsDetail = goodsService.getGoodsDetail(goods_id);
        GoodsLineDetail goodsLineDetails = (GoodsLineDetail) goodsDetail.get("goodsLineDetails");
        Boolean user_follow_goods = (Boolean) goodsDetail.get("user_follow_goods");
        Integer goodsFollowNumber = (Integer) goodsDetail.get("goodsFollowNumber");
        Float goodsPraisePercentage = (Float) goodsDetail.get("goodsPraisePercentage");
        Integer commentNumber = (Integer) goodsDetail.get("commentNumber");


        return R.ok().data("goodsLineDetails",goodsLineDetails)
                     .data("user_follow_goods",user_follow_goods)
                     .data("goodsFollowNumber",goodsFollowNumber)
                     .data("goodsPraisePercentage",goodsPraisePercentage)
                     .data("commentNumber",commentNumber);
    }






    //测试方法
    @GetMapping("/getHello")
    @SentinelResource(value = "getHello", blockHandler = "BashBlockHandler")
    public String getHello() {
//        System.out.println("进入了目标方法....api");
//        int i = storeStarService.rmStore(store_id);
        return  "测试Sentinel";

    }


    //生产者角色：提供给其它模块根据goods_style_id获取价格price
    @GetMapping("/getGoodsPrice")
    public BigDecimal getGoodsPrice(@RequestParam("goods_style_id") String goods_style_id) {
        GoodsStyle goodsStyle = goodsStyleService.getById(goods_style_id);
        return goodsStyle.getPrice();
    }

    //生产者角色：提供给其它模块根据goods_style_id获取goods_id
    @GetMapping("/getGoodsId")
    public String getGoodsId(@RequestParam("goods_style_id") String goods_style_id) {
        GoodsStyle goodsStyle = goodsStyleService.getById(goods_style_id);

        return goodsStyle.getGoodsId();
    }

    //生产者角色：提供给其它模块根据goods_id获取goods信息
    @GetMapping("/getGoodsByGoodsId")
    public Goods getGoodsByGoodsId(@RequestParam("goods_id") String goods_id) {
        Goods goods = goodsService.getById(goods_id);
        return goods;

    }












}

