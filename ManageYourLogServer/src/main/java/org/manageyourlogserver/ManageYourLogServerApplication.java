package org.manageyourlogserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.manageyourlogserver.dao.mysql")
public class ManageYourLogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogServerApplication.class, args);
    }

}
