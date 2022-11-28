package org.manage.log.config.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author cartoon
 * @date 2022/6/4 17:43
 */
@SpringBootApplication(scanBasePackages = {"org.manage.log"}, exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo
public class ManageYourLogConfigProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogConfigProviderApplication.class, args);
    }

}
