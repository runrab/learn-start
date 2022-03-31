package cn.edu.zut.gradesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.zut.gradesign.dao")
public class GraDesignApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraDesignApplication.class, args);
    }

}
