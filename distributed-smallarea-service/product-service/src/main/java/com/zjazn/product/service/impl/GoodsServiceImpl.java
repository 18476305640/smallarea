package com.zjazn.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjazn.common.common.AuthenticationToUserDto;
import com.zjazn.product.entity.Goods;
import com.zjazn.product.entity.GoodsTop;
import com.zjazn.product.entity.vo.GoodsLineDetail;
import com.zjazn.product.mapper.GoodsMapper;
import com.zjazn.product.mapper.GoodsTopMapper;
import com.zjazn.product.service.GoodsService;
import com.zjazn.product.service.GoodsTopService;
import com.zjazn.product.service.StoreStarService;
import com.zjazn.common.common.UserDto;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-22
 */
@Service
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private GoodsTopService goodsTopService;

    @Resource
    private GoodsTopMapper goodsTopMapper;
    @Resource
    private StoreStarService storeStarService;

    @Override
//    @GlobalTransactional(name = "fsp-test" , rollbackFor = Exception.class)
    public Map<String, Object> getGoodsDetail(String goods_id) {

        HashMap<String, Object> map = new HashMap<>();
        //1、根据goods_id获取商品的详细信息，用于作页面的整体显示
        GoodsLineDetail goodsLineDetails = goodsMapper.GoodsLineDetail(goods_id);

        //2、根据goods_id查看该用户是否关注了该商品
        UserDto userDto = AuthenticationToUserDto.getUserDto();
        QueryWrapper<GoodsTop> goodsTopWrapper = new QueryWrapper<>();
        goodsTopWrapper.eq("user_id",userDto.getId());
        goodsTopWrapper.eq("goods_id",goods_id);
        GoodsTop one = goodsTopService.getOne(goodsTopWrapper);
        Boolean user_follow_goods=(one==null)?false:true;
        //3、计算有多少人关注了该商品
        Integer goodsFollowNumber = goodsTopMapper.getGoodsFollowNumber(goods_id);
        //4、计算好评率
        Float goodsPraisePercentage = storeStarService.getGoodsPraisePercentage(goods_id);
        System.out.println("好评率计算失败返回："+goodsPraisePercentage);
        //5、计算有多少人评论了该商品
        Integer commentNumber = storeStarService.getCommentByGoodsId(goods_id);
        map.put("goodsLineDetails",goodsLineDetails);
        map.put("user_follow_goods",user_follow_goods);
        map.put("goodsFollowNumber",goodsFollowNumber);
        map.put("goodsPraisePercentage",goodsPraisePercentage);
        map.put("commentNumber",commentNumber);
        return map;
    }



}
