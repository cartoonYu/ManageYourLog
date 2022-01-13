package org.manageyourlog.test.server.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.server.config.ApplicationConfigKey;
import org.manageyourlog.server.repository.DefaultLogRecordRepository;
import org.manageyourlog.server.repository.LogRecordRepository;
import org.manageyourlog.server.repository.factory.StoreRepositoryFactory;
import org.manageyourlog.test.base.BaseTest;
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
    private ApplicationConfig applicationConfig;

    @Autowired
    @InjectMocks
    private StoreRepositoryFactory storeRepositoryFactory;

    @DisplayName("init bean default test")
    @Test
    public void testInitBeanDefault(){
        applicationConfig.setEnvironment(BaseTest.environment);
        Mockito.when(applicationConfig.get(ApplicationConfigKey.storeMode.getKey())).thenReturn(Optional.empty());
        LogRecordRepository logRecordRepository = storeRepositoryFactory.initPrimaryRepository();
        Assertions.assertTrue(logRecordRepository instanceof DefaultLogRecordRepository);
    }
}
