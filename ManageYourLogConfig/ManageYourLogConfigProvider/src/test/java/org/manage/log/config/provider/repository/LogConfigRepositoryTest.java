package org.manage.log.config.provider.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.manage.log.config.provider.base.BaseTest;
import org.manage.log.config.provider.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author cartoon
 * @date 2022/6/5 18:53
 */
@DisplayName("log config repository")
public class LogConfigRepositoryTest extends BaseTest {

    @Autowired
    private List<LogConfigRepository> logConfigRepositories;

    @DisplayName("add test")
    @Test
    @Order(1)
    public void add(){
        logConfigRepositories.forEach(logConfigRepository -> Assertions.assertTrue(logConfigRepository.add(DefineModelUtil.defineLogConfig())));
    }

    @DisplayName("get by config name test")
    @Test
    @Order(1)
    public void getByConfigName(){
        logConfigRepositories.forEach(logConfigRepository -> Assertions.assertNotNull(logConfigRepository.getByConfigName("orderOperate")));
    }

    @DisplayName("get all test")
    @Test
    @Order(2)
    public void getAll(){
        logConfigRepositories.forEach(logConfigRepository -> Assertions.assertEquals(logConfigRepositories.size() + 1, logConfigRepository.getAll().size()));
    }
}
