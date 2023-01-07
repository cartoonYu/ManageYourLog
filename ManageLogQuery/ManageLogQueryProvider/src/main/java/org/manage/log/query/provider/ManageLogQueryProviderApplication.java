package org.manage.log.query.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"org.manage.log"}, exclude = {DataSourceAutoConfiguration.class})
public class ManageLogQueryProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageLogQueryProviderApplication.class, args);
    }

}
