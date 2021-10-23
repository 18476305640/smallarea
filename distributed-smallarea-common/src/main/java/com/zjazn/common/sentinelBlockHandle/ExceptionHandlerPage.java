package com.zjazn.common.sentinelBlockHandle;


import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义sentinel异常返回信息,直接代替原有的blockhandle返回
 */
@Component
public class ExceptionHandlerPage implements UrlBlockHandler {
    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        // BlockException 异常接口，其子类为Sentinel五种规则异常的实现类
        // AuthorityException 授权异常
        // DegradeException 降级异常
        // FlowException 限流异常
        // ParamFlowException 参数限流异常
        // SystemBlockException 系统负载异常
        ResponseData data = new ResponseData();
        if (e instanceof FlowException) {
            data = new ResponseData(20001, "接口被限流了。");
        } else if (e instanceof DegradeException) {
            data = new ResponseData(20001, "接口被降级了。");
        }

        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(data));
    }
}

/**
 * 定义返回的实体类，字段根据需要添加
 */
@Data       // 生成getter/setter/tostring/equals
@AllArgsConstructor // 全参构造
@NoArgsConstructor  // 无参构造
class ResponseData {
    private int code;
    private String message;
}