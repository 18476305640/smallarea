package com.zjazn.cart.service;

import com.zjazn.cart.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-02
 */
public interface CartService extends IService<Cart> {
    public Cart getMyCart(String user_id, String store_id);

}
