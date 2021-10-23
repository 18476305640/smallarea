package com.zjazn.store.mapper;

import com.zjazn.store.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-07-06
 */
public interface StoreMapper extends BaseMapper<Store> {
    Float getStorePraisePercentage(@Param("store_id") String store_id);
}
