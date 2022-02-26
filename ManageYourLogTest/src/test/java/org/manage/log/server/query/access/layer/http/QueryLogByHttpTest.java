package org.manage.log.server.query.access.layer.http;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.base.BaseTest;

/**
 * @author cartoon
 * @date 2022/2/7 14:13
 */
@DisplayName("query log by http test")
public class QueryLogByHttpTest extends BaseTest {

    @DisplayName("query by index list")
    @Test
    public void testQueryByIndex() throws Exception{
        String result = get("/query/queryByIndex", ImmutableList.of(ImmutablePair.of("index", "111")));
        Assertions.assertNotNull(result);
    }
}
