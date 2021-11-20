package org.manageyourlog.test.base;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.manageyourlog.test.ManageYourLogTestApplication;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 17:52
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = ManageYourLogTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ExtendWith(MockitoExtension.class)
public abstract class BaseTest {
}
