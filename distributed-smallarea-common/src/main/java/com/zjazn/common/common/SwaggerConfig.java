package com.zjazn.common.common;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2 //swagger注解
public class SwaggerConfig {

    //添加一个组
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                //配置组名，是否雇用这个组， 组的信息配置ApiInfo，其它配置
                .groupName("groupA").enable(true).apiInfo(webApiInfo()).select()
                //指定扫描的路径：any()扫描全部   none() 不扫描  basePackage() 指定要扫描的包
                .apis(RequestHandlerSelectors.any())
                //过滤哪个路径：paths() 过滤什么路径
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                //整合oauth2
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));

    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("小社区API文档")
                .description("编码人：小庄，本文档描述了课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("小庄的BLog", "https://www.cnblogs.com/zjazn/", "2119299531@qq.com"))
                .build();
    }

    /*
    * Swagger注释的使用：
    *       Model:  类：@ApiModel("用户实体类")   属性： @ApiModelProperty("用户名")    ，但Controller 返回的这个Model时，会在SwaggerUI的Model上显示。
    *
    *       Controller:  接口方法上：  @ApiOperation("根据ID获取用户信息")
    *
    *
    * */


    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }


    /**
     * swagger2 认证的安全上下文
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("web", "access_token");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("Bearer",authorizationScopes));
    }
}
