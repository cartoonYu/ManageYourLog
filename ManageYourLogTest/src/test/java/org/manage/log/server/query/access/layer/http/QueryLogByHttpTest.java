package org.manage.log.server.query.access.layer.http;

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
        String queryResult = get("/query/queryByIndex",
                                    ImmutablePair.of("index", "111"));
        Assertions.assertNotNull(queryResult);
    }

    @DisplayName("query by between time")
    @Test
    public void testQueryByBetweenTime() throws Exception{
        String queryResult = get("/query/queryBetweenTime",
                                    ImmutablePair.of("startTime", "2021-10-26 00:00:00"),
                                    ImmutablePair.of("endTime", "2021-10-27 00:00:00"));
        Assertions.assertNotNull(queryResult);
    }

    @DisplayName("query by index and time")
    @Test
    public void testQueryByIndexBetweenTime() throws Exception {
        String queryResult = get("/query/queryByIndexBetweenTime",
                                    ImmutablePair.of("index", "111"),
                                    ImmutablePair.of("startTime", "2021-10-26 00:00:00"),
                                    ImmutablePair.of("endTime", "2021-10-27 00:00:00"));
        Assertions.assertNotNull(queryResult);
    }
}
