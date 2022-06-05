package org.manage.log.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 17:50
 */
@SpringBootApplication(scanBasePackages = {"org.manage.log.config", "org.manage.log.common"}, exclude = {DataSourceAutoConfiguration.class})
public class ManageYourLogConfigTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogConfigTestApplication.class, args);
    }

}
