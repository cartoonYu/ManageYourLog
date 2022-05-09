package org.manage.log.receive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"org.manage.log"}, exclude = {DataSourceAutoConfiguration.class})
public class ManageYourLogReceiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogReceiveApplication.class, args);
    }

}
