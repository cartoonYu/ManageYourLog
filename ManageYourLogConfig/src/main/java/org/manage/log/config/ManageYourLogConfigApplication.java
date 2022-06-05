package org.manage.log.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author cartoon
 * @date 2022/6/4 17:43
 */
@SpringBootApplication(scanBasePackages = {"org.manage.log"}, exclude = {DataSourceAutoConfiguration.class})
public class ManageYourLogConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogConfigApplication.class, args);
    }

}
