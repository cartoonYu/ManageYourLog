package org.manage.log.config.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author cartoon
 * @since 2022/11/24 01:25
 */
@SpringBootApplication(scanBasePackages = {"org.manage.log"}, exclude = {DataSourceAutoConfiguration.class})
public class ManageYourLogConfigProviderTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogConfigProviderApplication.class, args);
    }
}
