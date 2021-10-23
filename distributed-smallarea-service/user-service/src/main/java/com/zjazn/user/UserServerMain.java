package com.zjazn.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源的自带创建
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.zjazn"})
@MapperScan("com.zjazn.user.mapper")
public class UserServerMain {
    public static void main(String[] args) {
        SpringApplication.run(UserServerMain.class,args);

    }

}
