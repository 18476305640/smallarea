package com.zjazn.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 * @version 1.0
 * 安全拦截机制
 **/
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //这里放行后，不用进行权限认证
                .antMatchers("/v2/*", "/druid/*","/swagger-ui.html","/webjars/**",
                        "/swagger-resources/**",
                        "/swagger-ui.html","/webjars/**"
                ).permitAll()
                //.antMatchers("/test").hasAuthority("p2")  //访问/test需要有p2权限
                .antMatchers("/**").authenticated()//所有/**的请求必须认证通过
                .anyRequest().permitAll()  //拦截匹配上面的规则，其它放行
                .and().formLogin();


    }



    /**
     * 配置哪些请求不拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**",
                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/doc.html/**"
        );
    }

}

/**
 * 解决在yml中加入这段配置导致静态资源无法访问的问题，比如swagger ui
 * resources:
 *     add-mappings: false
 */
@Component
class WebSecurityConfigStaticFree implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/", "/static", "/public");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
