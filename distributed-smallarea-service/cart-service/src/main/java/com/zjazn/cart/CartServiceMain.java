package com.zjazn.cart;

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
@EnableFeignClients
@ComponentScan("com.zjazn")
@MapperScan(basePackages = {"com.zjazn.cart.mapper"})
public class CartServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(CartServiceMain.class,args);
    }
}
