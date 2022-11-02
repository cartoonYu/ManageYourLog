package org.manage.log.common.util.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cartoon.yu
 * @since 2022/11/2 18:43
 */
@DisplayName("application config util test")
public class ApplicationConfigUtilTest {

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    @Test
    @DisplayName("[normal] test get config by key")
    public void testGetConfigByKey(){

    }
}
