package org.manage.log.query.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.query.base.BaseTest;
import org.manage.log.query.repository.config.ApplicationConfigKey;
import org.manage.log.query.repository.factory.QueryRepositoryFactory;
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
    private QueryRepositoryFactory queryRepositoryFactory;

    @DisplayName("init bean default test")
    @Test
    public void testInitBeanDefault(){
        applicationConfigUtil.setEnvironment(environment);
        Mockito.when(applicationConfigUtil.get(ApplicationConfigKey.storeMode.getKey())).thenReturn(Optional.empty());
        LogRecordRepository logRecordRepository = queryRepositoryFactory.initPrimaryRepository();
        Assertions.assertTrue(logRecordRepository instanceof LogRecordMysqlRepository);
    }
}
