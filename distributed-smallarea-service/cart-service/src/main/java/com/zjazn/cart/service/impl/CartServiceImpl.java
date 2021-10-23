package com.zjazn.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjazn.cart.entity.Cart;
import com.zjazn.cart.mapper.CartDetailMapper;
import com.zjazn.cart.mapper.CartMapper;
import com.zjazn.cart.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-02
 */
@Service
@Slf4j
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Resource
    private CartMapper cartMapper;

    @Resource
    private CartDetailMapper cartDetailMapper;

    @Override
    public Cart getMyCart(String user_id, String store_id) {
        //查看是否存在某个用户与商店的购物车，如果存在则直接返回，如果不存在创建返回
        LambdaQueryWrapper<Cart> cart_qw = new LambdaQueryWrapper<>();
        cart_qw.eq(Cart::getUserId,user_id);
        cart_qw.eq(Cart::getStoreId,store_id);
        Cart cart = cartMapper.selectOne(cart_qw);
        if(cart == null) {
            log.info("不存在购物车，正在创建！");
            Cart c = new Cart();
            c.setStoreId(store_id);
            c.setUserId(user_id);
            int insert = cartMapper.insert(c);
            cart = cartMapper.selectOne(cart_qw);
            log.info("正在创建结果："+insert+"，查询到："+cart);
        }

        return cart;
    }
}
