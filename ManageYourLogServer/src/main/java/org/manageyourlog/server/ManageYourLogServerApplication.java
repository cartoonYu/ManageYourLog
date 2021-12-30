package org.manageyourlog.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"org.manageyourlog"}, exclude = {DataSourceAutoConfiguration.class})
public class ManageYourLogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogServerApplication.class, args);
    }

}
