package org.manageyourlogtest;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 17:50
 */
@SpringBootApplication(scanBasePackages = {"org.manageyourlogserver"})
@MapperScan("org.manageyourlogserver.dao.mysql")
public class ManageYourLogTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogTestApplication.class, args);
    }


}
