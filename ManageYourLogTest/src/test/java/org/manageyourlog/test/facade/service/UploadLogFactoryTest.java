package org.manageyourlog.test.facade.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.common.config.ApplicationConfigKey;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.service.UploadLogByDefault;
import org.manageyourlog.facade.service.UploadLogFactory;
import org.manageyourlog.test.base.BaseTest;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.Optional;

/**
 * @author cartoon
 * @date 2021/12/7 11:21
 */
@DisplayName("upload log service factory test")
public class UploadLogFactoryTest extends BaseTest{

    @Spy
    private ApplicationConfig applicationConfig;

    @Autowired
    @InjectMocks
    private UploadLogFactory uploadLogFactory;

    @DisplayName("upload log service factory test")
    @Test
    public void testInitBeanDefault(){
        applicationConfig.setEnvironment(BaseTest.environment);
        Mockito.when(applicationConfig.get(ApplicationConfigKey.uploadLogMode)).thenReturn(Optional.empty());
        UploadLog uploadLog = uploadLogFactory.initPrimarySendLogService();
        Assertions.assertTrue(uploadLog instanceof UploadLogByDefault);
    }

}
