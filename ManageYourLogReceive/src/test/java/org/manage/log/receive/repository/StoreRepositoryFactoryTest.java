package org.manage.log.receive.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.receive.base.BaseTest;
import org.manage.log.receive.repository.config.ReceiveApplicationConfigKey;
import org.manage.log.receive.repository.factory.ReceiveRepositoryFactory;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/16 00:10
 */
@DisplayName("store repository factory test")
public class StoreRepositoryFactoryTest extends BaseTest {

    @Spy
    private ApplicationConfigUtil applicationConfigUtil;

    @Autowired
    @InjectMocks
    private ReceiveRepositoryFactory receiveRepositoryFactory;

    @DisplayName("init bean default test")
    @Test
    public void testInitBeanDefault(){
        applicationConfigUtil.setEnvironment(environment);
        Mockito.when(applicationConfigUtil.get(ReceiveApplicationConfigKey.storeMode.getKey())).thenReturn(Optional.empty());
        LogRecordRepository logRecordRepository = receiveRepositoryFactory.initPrimaryRepository();
        Assertions.assertTrue(logRecordRepository instanceof LogRecordMysqlRepository);
    }
}
