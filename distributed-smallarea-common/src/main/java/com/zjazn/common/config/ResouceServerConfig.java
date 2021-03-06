package com.zjazn.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResouceServerConfig extends
        ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "res1";

    //2、jwt后加入
    @Autowired
    TokenStore tokenStore;



    //3、替换配置
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID)
//                .tokenServices(tokenService()) //jwt前
                .tokenStore(tokenStore) //jwt后
                .stateless(true);
    }
    //放行后，将直接可以访问，如果该接口要被服务调用, 需要开放！
    private static final String[] AUTH_LIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger**",
            "/webjars/**",
            "/test",
//            "/getHello",

            //公共
            "/druid/**",
            "/token**",


            //Store模块开放的API
            "/getGoodsPraisePercentage",
            "/getStarByGoodsIdList",
            "/getCommentByGoodsId",
            "/rmStore",
            "/getStoreById",
            "/getGoodsPraisePercentage",
            "/getStorePraisePercentage",
            //product开放的api接口
            "/getGoodsPrice",
            "/getGoodsId",
            "/getGoodsByGoodsId",
            "/getGoodsStyleList",
            "/getGoodsStyleById",
            "/getGoodsStyleOneById"


    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(AUTH_LIST).permitAll()
                .antMatchers("/**").access("#oauth2.hasScope('ROLE_ADMIN')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


    //1、jwt屏蔽下面这个方法
//    //资源服务令牌解析服务
//    @Bean
//    public ResourceServerTokenServices tokenService() {
//        //使用远程服务请求授权服务器校验token,必须指定校验token 的url、client_id,client_secret
//        //当访问资源时，会调用远程授权服务器检验token是否正确
//        RemoteTokenServices service=new RemoteTokenServices();
//        service.setCheckTokenEndpointUrl("http://localhost:53020/uaa/oauth/check_token");
//        service.setClientId("c1");
//        service.setClientSecret("secret");
//        return service;
//    }



}
