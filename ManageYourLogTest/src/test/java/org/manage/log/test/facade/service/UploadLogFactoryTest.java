package org.manage.log.test.facade.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.facade.UploadLog;
import org.manage.log.facade.config.ApplicationConfigKey;
import org.manage.log.facade.service.UploadLogByDefault;
import org.manage.log.facade.service.factory.UploadLogFactory;
import org.manage.log.test.base.BaseTest;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author cartoon
 * @date 2021/12/7 11:21
 */
@DisplayName("upload log service factory test")
public class UploadLogFactoryTest extends BaseTest{

    @Spy
    private ApplicationConfigUtil applicationConfigUtil;

    @Autowired
    @InjectMocks
    private UploadLogFactory uploadLogFactory;

    @DisplayName("upload log service factory test")
    @Test
    public void testInitBeanDefault(){
        applicationConfigUtil.setEnvironment(environment);
        Mockito.when(applicationConfigUtil.get(ApplicationConfigKey.uploadLogMode.getKey())).thenReturn(Optional.empty());
        UploadLog uploadLog = uploadLogFactory.initPrimarySendLogService();
        Assertions.assertTrue(uploadLog instanceof UploadLogByDefault);
    }

}
