package com.zjazn.store.service.impl;

import com.zjazn.store.entity.Star;
import com.zjazn.store.entity.vo.goods_star;
import com.zjazn.store.mapper.StarMapper;
import com.zjazn.store.service.StarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-28
 */
@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star> implements StarService {

    @Resource
    private StarMapper starMapper;

    @Override
    public List<goods_star> getGoodsStar(String[] goods_ids) {
//        QueryWrapper<Star> starQueryWrapper = new QueryWrapper<>();
//        starQueryWrapper.select(" goods_id , AVG(star) as star");
//        starQueryWrapper.in("goods_id",goods_ids);
//        starQueryWrapper.groupBy("goods_id");

        List<goods_star> avgStarByGoods = starMapper.getAvgStarByGoods(goods_ids);

//        List<Star> stars = starMapper.selectList(starQueryWrapper);
        return avgStarByGoods;
    }
}
