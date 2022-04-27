package org.manage.log.receive.facade.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.base.BaseTest;
import org.manage.log.upload.UploadLog;
import org.manage.log.upload.config.ApplicationConfigKey;
import org.manage.log.upload.service.UploadLogByDefault;
import org.manage.log.upload.service.factory.UploadLogFactory;
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
