package org.manage.log.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"org.manage.log"}, exclude = {DataSourceAutoConfiguration.class})
public class ManageYourLogQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogQueryApplication.class, args);
    }

}
