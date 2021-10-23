package com.zjazn.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源的自带创建
//@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.zjazn"})
@MapperScan("com.zjazn.product.mapper")
@EnableFeignClients  //激活OpenFeign
public class ProductServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceMain.class,args);
    }
}
