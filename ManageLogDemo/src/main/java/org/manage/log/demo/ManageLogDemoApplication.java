package org.manage.log.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(scanBasePackages = {"org.manage.log"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ManageLogDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageLogDemoApplication.class, args);
    }

}
