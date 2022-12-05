package org.manage.log.receive.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"org.manage.log"}, exclude = {DataSourceAutoConfiguration.class})
public class ManageYourLogReceiveProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogReceiveProviderApplication.class, args);
    }

}
