package org.manage.log.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 17:50
 */
@SpringBootApplication(scanBasePackages = {"org.manage.log.upload", "org.manage.log.common"}, exclude = {DataSourceAutoConfiguration.class})
public class ManageYourLogUploadTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManageYourLogUploadTestApplication.class, args);
    }

}
