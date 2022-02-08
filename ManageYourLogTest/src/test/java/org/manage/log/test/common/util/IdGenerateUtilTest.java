package org.manage.log.test.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.test.base.BaseTest;
import org.manage.log.common.util.IdGenerateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cartoon
 * @date 2021/10/23 17:59
 */
@DisplayName("id generate util test")
public class IdGenerateUtilTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(IdGenerateUtilTest.class);

    @DisplayName("generate id normal test")
    @Test
    public void testGenerateNormal(){
        String res = IdGenerateUtil.getInstance().generate(13);
        Assertions.assertNotNull(res);
        Assertions.assertEquals(13, res.length());
    }

    @DisplayName("generate id with over length test")
    @Test
    public void testGenerateOverLength(){
        String res = IdGenerateUtil.getInstance().generate(19);
        Assertions.assertNotNull(res);
        Assertions.assertEquals(19, res.length());
    }

    @DisplayName("generate id with zero length test")
    @Test
    public void testGenerateZeroLength(){
        String res = IdGenerateUtil.getInstance().generate(0);
        Assertions.assertNotNull(res);
        Assertions.assertEquals(0, res.length());
    }
}
