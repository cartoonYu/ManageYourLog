package org.manageyourlog.test.facade.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.common.util.config.ApplicationConfigUtil;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.config.ApplicationConfigKey;
import org.manageyourlog.facade.service.factory.UploadLogFactory;
import org.manageyourlog.facade.service.http.UploadLogByHttp;
import org.manageyourlog.test.base.BaseTest;
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
        applicationConfigUtil.setEnvironment(BaseTest.environment);
        Mockito.when(applicationConfigUtil.get(ApplicationConfigKey.uploadLogMode.getKey())).thenReturn(Optional.empty());
        UploadLog uploadLog = uploadLogFactory.initPrimarySendLogService();
        Assertions.assertTrue(uploadLog instanceof UploadLogByHttp);
    }

}
