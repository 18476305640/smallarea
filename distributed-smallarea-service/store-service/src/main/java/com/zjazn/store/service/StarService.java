package com.zjazn.store.service;

import com.zjazn.store.entity.Star;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjazn.store.entity.vo.goods_star;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-28
 */
public interface StarService extends IService<Star> {
    List<goods_star> getGoodsStar(String[] goods_ids);


}
