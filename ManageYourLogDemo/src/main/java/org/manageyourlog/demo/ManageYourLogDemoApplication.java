package org.manageyourlog.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.manageyourlog.demo", "org.manageyourlog.facade"})
public class ManageYourLogDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogDemoApplication.class, args);
    }

}
