package com.runrab.gmall.mock.db;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.runrab.gmall.mock.db.mapper")
public class GmallMockDBApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GmallMockDBApplication.class, args);

        MockTask mockTask = context.getBean(MockTask.class);

        mockTask.mainTask();
    }
}
