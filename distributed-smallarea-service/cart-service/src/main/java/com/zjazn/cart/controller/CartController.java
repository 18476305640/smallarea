package com.zjazn.cart.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjazn.common.common.AuthenticationToUserDto;
import com.zjazn.common.common.UserDto;
import com.zjazn.common.conduit.R;
import com.zjazn.cart.entity.Cart;
import com.zjazn.cart.entity.CartDetail;
import com.zjazn.cart.entity.vo.*;
import com.zjazn.cart.mapper.CartMapper;
import com.zjazn.cart.service.CartDetailService;
import com.zjazn.cart.service.CartService;
import com.zjazn.cart.service.GoodsService;
import com.zjazn.cart.service.StoreService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-01
 */
@RestController
public class CartController {
    /*
    * 购物车需要写的接口：
    * 1、查看是否存在某个用户与商店的购物车，如果存在则直接返回，如果不存在创建返回
    * 2、向购物车中添加商品
    * 3、向购物车中删除商品
    * 4、更新商品信息
    * 5、删除购物车
    *
    *
    * */
    @Resource
    private CartService cartService;
    @Resource
    private CartMapper cartMapper;
    @Resource
    private CartDetailService cartDetailService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private StoreService storeService;
    @ApiOperation("查看是否存在某个用户与商店的购物车，如果存在则直接返回，如果不存在创建返回")
    @GetMapping("/getMyCart")
    public R getMyCart(@RequestParam("goods_id") String goods_id) {
        UserDto userDto = AuthenticationToUserDto.getUserDto();
        String user_id = userDto.getId();
        String store_id = goodsService.getGoodsByGoodsId(goods_id).getStoreId();

        Cart myCart = cartService.getMyCart(user_id,store_id);
        if(myCart == null) {
            return R.error().message("购物车获取失败！");
        }
        LoadCart loadCart = new LoadCart();
        loadCart.setId(myCart.getId());
        loadCart.setStoreId(myCart.getStoreId());
        loadCart.setUserId(myCart.getUserId());
        QueryWrapper<CartDetail> cd_qw = new QueryWrapper<>();
        cd_qw.eq("cart_id",myCart.getId());
        List<CartDetail> list = cartDetailService.list(cd_qw);
        loadCart.setCartDetailList(list);
        return R.ok().data("loadCart",loadCart);

    }

    @PostMapping("/addGoodsToCart")
    @ApiOperation("向购物车中添加商品")
    public R addGoodsToCart(@RequestBody GoodsToCart goodsToCart) {
        //根据goods_Style_id获取价格然后跟数量进行计算获取商品xN价格
        BigDecimal goodsPrice;
        try {
            goodsPrice = goodsService.getGoodsPrice(goodsToCart.getGoods_style_id());
        }catch (Exception e) {
            return R.error().message("更新购物车失败，原因无法获取样式商品价格！");
        }
        //根据goods_Style_id获取是哪个商品
        String goods_id;
        try {
            goods_id = goodsService.getGoodsId(goodsToCart.getGoods_style_id());
        }catch (Exception e) {
            return R.error().message("更新购物车失败，原因无法获取样式商品id！");
        }
        //根据goods_style_id查询goods_style记录
        GoodsStyle goodsStyle = goodsService.getGoodsStyleOneById(goodsToCart.getGoods_style_id());
        CartDetail cd = new CartDetail();
        cd.setGoodsId(goods_id);
        cd.setGoodsStyleId(goodsToCart.getGoods_style_id());
        cd.setCount(goodsToCart.getCount());
        cd.setCover(goodsStyle.getCover());
        //设置价格
        cd.setPrice(goodsPrice.multiply(BigDecimal.valueOf(goodsToCart.getCount())));
        //根据GoodsId与用户Id查询购物车
        QueryWrapper<Cart> cart_qw = new QueryWrapper<>();
        cart_qw.eq("user_id", AuthenticationToUserDto.getUserDto().getId());
        //根据Goods_id查询商家id
        Goods goods = goodsService.getGoodsByGoodsId(goods_id);
        cart_qw.eq("store_id", goods.getStoreId());
        Cart cart = cartService.getOne(cart_qw);
        cd.setCartId(cart.getId());

        boolean save = cartDetailService.save(cd);
        QueryWrapper<CartDetail> added_cd_qw = new QueryWrapper<>();
        added_cd_qw.eq("cart_id", cart.getId());
        List<CartDetail> loadCart = cartDetailService.list(added_cd_qw);
        if(save && loadCart != null) {
            return R.ok().message("向购物车中添加商品成功!").data("loadCart",loadCart);
        }else {
            return R.error().message("向购物车中添加商品失败！");
        }
    }

    @PostMapping("/removeGoodsFromCart/{cart_id}")
    @ApiOperation("向购物车中删除商品")
    public R removeGoodsFromCart(@PathVariable("cart_id") String cart_id) {
        boolean b = cartDetailService.removeById(cart_id);
        if(b) {
            return R.ok().message("向购物车中拿出商品成功！");
        }else {
            return R.error().message("向购物车中拿出商品失败！");
        }
    }

    @PostMapping("/updateGoodsFromCart")
    @ApiOperation("根据goods_Style_id更新cartDetail信息")
    public R updateGoodsFromCart(@RequestBody CartDetail cartDetail) {
        CartDetail cd = new CartDetail();
        //根据goods_Style_id获取价格然后跟数量进行计算获取商品xN价格
        BigDecimal goodsPrice;
        try {
            goodsPrice = goodsService.getGoodsPrice(cartDetail.getGoodsStyleId());
        }catch (Exception e) {
            return R.error().message("更新购物车失败，原因无法获取样式商品价格！");
        }
        cd.setPrice(goodsPrice.multiply(BigDecimal.valueOf(cartDetail.getCount())));
        cd.setGoodsStyleId(cartDetail.getGoodsStyleId());
        cd.setId(cartDetail.getId());
        cd.setCount(cartDetail.getCount());
        boolean b = cartDetailService.updateById(cd);
        if(b) {
            CartDetail result_cd = cartDetailService.getById(cartDetail.getId());
            return R.ok().data("cartDetail",result_cd);
        }else {
            return R.error().message("更新购物车失败");
        }
    }
    @PostMapping("/removeCart/{cart_id}")
    @ApiOperation("删除购物车")
    public R removeCart(@PathVariable("cart_id") String cart_id ) {
        Boolean issuccess=false;
        boolean b = cartService.removeById(cart_id);
        if(b) {
            QueryWrapper<CartDetail> cd_qw = new QueryWrapper<>();
            cd_qw.eq("cart_id",cart_id);
            boolean c = cartDetailService.remove(cd_qw);
            if(c) {
               issuccess=true;
            }
        }
        if(issuccess) {
            return R.error().message("删除购物车成功！");
        }else {
            return R.error().message("删除购物车失败！");
        }

    }

    @GetMapping("/getMyCartDetail")
    @ApiOperation("根据前端传入的goods_id,购物车详情")
    public R getMyCartDetail(@RequestParam("goods_id") String goods_id) {
        UserDto userDto = AuthenticationToUserDto.getUserDto();
        String user_id = userDto.getId();
        String store_id = goodsService.getGoodsByGoodsId(goods_id).getStoreId();

        Cart myCart = cartService.getMyCart(user_id,store_id);
        if(myCart == null) {
            return R.error().message("购物车获取失败！");
        }
        //LoadCart loadCart = new LoadCart();
        ConfirmationCart confirmationCart = new ConfirmationCart();

        confirmationCart.setId(myCart.getId());
        confirmationCart.setStoreId(myCart.getStoreId());
        confirmationCart.setUserId(myCart.getUserId());
        QueryWrapper<CartDetail> cd_qw = new QueryWrapper<>();
        cd_qw.eq("cart_id",myCart.getId());
        List<CartDetail> list = cartDetailService.list(cd_qw);
        ArrayList<ConfirmationItem> confirmationItems = new ArrayList<>();
        for (CartDetail cartDetailF : list) {
            ConfirmationItem confirmationItem = new ConfirmationItem();
            BeanUtil.copyProperties(cartDetailF,confirmationItem);
            confirmationItems.add(confirmationItem);
        }
        //将CartDetail中的goods_style_id取出成数组,去请求它们的goods_style数据
        List<String> goodsStyleIds = new ArrayList<>();
        for (ConfirmationItem ci : confirmationItems) {
            goodsStyleIds.add(ci.getGoodsStyleId());
        }
        //得到的goods_style的数据,需要将之加入对应的list item中
        List<GoodsStyle>  goodsStyleList = goodsService.getGoodsStyleList(goodsStyleIds);
        for (ConfirmationItem c_item : confirmationItems) {
            for (GoodsStyle goodsStyle : goodsStyleList) {
                if(c_item.getGoodsStyleId().equals(goodsStyle.getId())) {
                    BeanUtil.copyProperties(goodsStyle,c_item);
                }

            }
        }
        confirmationCart.setConfirmationItems(confirmationItems);
        //查询商店的信息
        Store store = storeService.getStoreById(confirmationCart.getStoreId());
        //查询商店的好评率
        Float store_star = storeService.getStorePraisePercentage(store.getId());
        return R.ok().data("loadCart",confirmationCart).data("store",store).data("store_star",store_star);

    }

    @GetMapping("/altCartItemCount")
    @ApiOperation("根据前端传入的cart_item_id与count修改cart item")
    public R altCartItemCount(@RequestParam("cart_item_id") String cart_item_id, @RequestParam("count") Integer count ) {
        CartDetail cd = cartDetailService.getById(cart_item_id);
        //根据goods_Style_id获取数据
        GoodsStyle gd = goodsService.getGoodsStyleOneById(cd.getGoodsStyleId());
        BigDecimal price = gd.getPrice().multiply(BigDecimal.valueOf(count));
        //复制旧的CartDetail,然后修改要修改的内容
        CartDetail cartDetail = new CartDetail();
        BeanUtil.copyProperties(cd,cartDetail);
        cartDetail.setCount(count);
        cartDetail.setPrice(price);
        cartDetailService.updateById(cartDetail);

        return R.ok().data("cartDetail",cartDetail);
    }







}

