package com.zjazn.product.service;

import com.zjazn.product.entity.vo.SmallTypeDesc;
import com.zjazn.product.entity.vo.TypeDesc;
import com.zjazn.product.entity.GoodsTypeGlobal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-22
 */
public interface GoodsTypeGlobalService extends IService<GoodsTypeGlobal> {
    //查询全部的GoodsTypeGlobal对应表的记录
    public List<TypeDesc> getTypeDescs();

    //查询全部的二级分类SmallTypeDesc对应表的记录
    public List<SmallTypeDesc> getSmallTypeDescs();

}
