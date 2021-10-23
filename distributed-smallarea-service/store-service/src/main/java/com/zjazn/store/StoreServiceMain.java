package com.zjazn.store;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源的自带创建
//@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.zjazn"})
@MapperScan("com.zjazn.store.mapper")
public class StoreServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(StoreServiceMain.class,args);
    }
}
