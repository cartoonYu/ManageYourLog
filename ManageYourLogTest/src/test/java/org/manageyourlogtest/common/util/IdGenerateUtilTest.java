package org.manageyourlogtest.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlogcommon.util.IdGenerateUtil;
import org.manageyourlogtest.base.BaseTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cartoon.yu
 * @date 2021/10/23 17:59
 */
public class IdGenerateUtilTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(IdGenerateUtilTest.class);

    @Test
    public void testGenerateNormal(){
        String res = IdGenerateUtil.generate(13);
        log.info(res);
        Assertions.assertNotNull(res);
        Assertions.assertEquals(13, res.length());
    }

    @Test
    public void testGenerateOverLength(){
        String res = IdGenerateUtil.generate(19);
        log.info(res);
        Assertions.assertNotNull(res);
        Assertions.assertEquals(19, res.length());
    }
}
