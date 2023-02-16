package org.manage.log.query.provider.access.layer;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.base.test.BaseTest;
import org.manage.log.query.facade.model.QueryLogResp;

import java.util.Collections;
import java.util.List;


/**
 * @author cartoon
 * @date 2022/2/7 14:13
 */
@DisplayName("query log by http test")
public class QueryLogByHttpTest extends BaseTest {


    @DisplayName("query by index list")
    @Test
    public void testQueryByIndex() throws Exception{
        List<QueryLogResp> queryResult = getList("/query/queryByIndex",
                                    Collections.singletonList(ImmutablePair.of("index", "111")), QueryLogResp.class);
        Assertions.assertNotNull(queryResult);
    }

    @DisplayName("query by between time")
    @Test
    public void testQueryByBetweenTime() throws Exception{
        List<QueryLogResp> queryResult = getList("/query/queryBetweenTime",
                ImmutableList.of(ImmutablePair.of("startTime", "2021-10-26 00:00:00"),
                        ImmutablePair.of("endTime", "2021-10-27 00:00:00")), QueryLogResp.class);
        Assertions.assertNotNull(queryResult);
    }

    @DisplayName("query by index and time")
    @Test
    public void testQueryByIndexBetweenTime() throws Exception {
        List<QueryLogResp> queryResult = getList("/query/queryByIndexBetweenTime",
                ImmutableList.of(ImmutablePair.of("index", "111"),
                        ImmutablePair.of("startTime", "2021-10-26 00:00:00"),
                        ImmutablePair.of("endTime", "2021-10-27 00:00:00")), QueryLogResp.class);
        Assertions.assertNotNull(queryResult);
    }
}
