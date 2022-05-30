package org.manage.log.upload.base;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.manage.log.upload.ManageYourLogUploadTestApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;



/**
 * base test which divide web container
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 17:52
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = ManageYourLogUploadTestApplication.class)
public class BaseTest implements EnvironmentAware {

    protected static ApplicationContext context;

    protected static Environment environment;

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        BaseTest.environment = environment;
    }
}

