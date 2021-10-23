package com.zjazn.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjazn.product.entity.vo.SmallTypeDesc;
import com.zjazn.product.entity.vo.TypeDesc;
import com.zjazn.product.entity.GoodsTypeGlobal;
import com.zjazn.product.mapper.GoodsTypeGlobalMapper;
import com.zjazn.product.service.GoodsTypeGlobalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-22
 */
@Service
public class GoodsTypeGlobalServiceImpl extends ServiceImpl<GoodsTypeGlobalMapper, GoodsTypeGlobal> implements GoodsTypeGlobalService {
    @Resource
    private GoodsTypeGlobalMapper goodsTypeGlobalMapper;

    //查询全部的GoodsTypeGlobal对应表的记录
    public List<TypeDesc> getTypeDescs() {
        LambdaQueryWrapper<GoodsTypeGlobal>  goodsTypeGlobalLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsTypeGlobalLambdaQueryWrapper.eq(GoodsTypeGlobal::getParentId,0);
        List<GoodsTypeGlobal> goodsTypeGlobals = goodsTypeGlobalMapper.selectList(goodsTypeGlobalLambdaQueryWrapper);

        List<TypeDesc> typeDescs = new ArrayList<>();
        for(int i=0; i<goodsTypeGlobals.size(); i++) {
            TypeDesc typeDesc = new TypeDesc();
            BeanUtils.copyProperties(goodsTypeGlobals.get(i),typeDesc);
            typeDescs.add(typeDesc);
        }

        return typeDescs;

    }

    //查询全部的二级分类SmallTypeDesc对应表的记录
    public List<SmallTypeDesc> getSmallTypeDescs() {
        LambdaQueryWrapper<GoodsTypeGlobal>  goodsTypeGlobalLambdaQueryWrapper = new LambdaQueryWrapper<>();
        goodsTypeGlobalLambdaQueryWrapper.ne(GoodsTypeGlobal::getParentId,0);
        List<GoodsTypeGlobal> goodsTypeGlobals = goodsTypeGlobalMapper.selectList(goodsTypeGlobalLambdaQueryWrapper);
        List<SmallTypeDesc> smallTypeDescs = new ArrayList<>();
        for(int i=0; i<goodsTypeGlobals.size(); i++) {
            SmallTypeDesc smallTypeDesc = new SmallTypeDesc();
            BeanUtils.copyProperties(goodsTypeGlobals.get(i),smallTypeDesc);
            smallTypeDescs.add(smallTypeDesc);
        }

        return smallTypeDescs;

    }



}
