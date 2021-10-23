package com.zjazn.gateway.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class CustomerGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        //如果url中满足下面任务一个就会开放
        if (isFree(exchange, "swagger","/uaa","/v2/","druid")) {
            //UAA Request
            return chain.filter(exchange); //放开
        }

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (token == null || token == "") {
            //没有认证token，不转发到微服务
            return noPower(exchange);
        }


        return chain.filter(exchange); //放开




    }

    @Override
    public int getOrder() {
        return 10;
    }

    private Mono<Void> noPower(ServerWebExchange serverWebExchange) {
        // 权限不够拦截
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);

        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(JSON.toJSONString("{status:4404,mession:'没有权限！'}").getBytes(StandardCharsets.UTF_8));
        ServerHttpResponse response = serverWebExchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));

    }

    //判断请求路径是否以是指定字符开头，不是返回-1,0是
    private Boolean isFree(ServerWebExchange exchange, String ...requestUriHeadString) {
        String requestUrl = exchange.getRequest().getURI().getRawPath();
        Boolean free = false;
        for(int i=0; i<requestUriHeadString.length; i++) {
            if(requestUrl.indexOf(requestUriHeadString[i]) != -1) {
                free = true;
                break;
            }
        }
        System.out.println(requestUrl+",即将返回："+free);
        return free;
    }



}
