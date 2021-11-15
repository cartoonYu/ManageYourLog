package org.manageyourlog.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.manageyourlog"})
public class ManageYourLogServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogServerApplication.class, args);
    }

}
