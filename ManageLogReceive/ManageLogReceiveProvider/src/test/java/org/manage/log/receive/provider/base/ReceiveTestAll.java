package org.manage.log.receive.provider.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.manage.log.base.test.BaseTest;
import org.manage.log.receive.facade.UploadLog;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/6 20:37
 */
@Suite
@SelectPackages({"org.manage.log.receive.provider"})
@ExcludePackages({"org.manage.log.receive.provider.base"})
public class ReceiveTestAll extends BaseTest {

    @MockBean
    private UploadLog uploadLog;

    @AfterEach
    private void reset(){
        Mockito.reset(uploadLog);
    }
}
