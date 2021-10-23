package com.zjazn.common.sentinelBlockHandle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.zjazn.common.conduit.R;

public class MyBlockHandler {
    /**
     *
     * 使用方法，在API方法上加上这样的配置： @SentinelResource(value = "globalBlockHandler",
     *             blockHandlerClass = BashBlockHandler.class,
     *             blockHandler = "BashBlockHandler")
     * */
    public static R BashBlockHandler(BlockException e) {
        return R.error().message("访问太过频繁或现时间段访问的人数过多，请稍后再访问！");

    }


}
