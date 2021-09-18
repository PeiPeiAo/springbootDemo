package com.example.springdome;

import com.example.springdome.bean.Testtable;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.example.springdome.mappers"})
@SpringBootApplication
public class SpringDomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDomeApplication.class, args);
    }

}
