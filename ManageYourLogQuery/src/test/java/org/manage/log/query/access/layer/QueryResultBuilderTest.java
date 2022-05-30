package org.manage.log.query.access.layer;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.manage.log.common.model.LogRecord;
import org.manage.log.query.access.layer.http.builder.QueryResultBuilder;
import org.manage.log.query.access.layer.http.model.QueryLogResp;
import org.manage.log.query.base.BaseTest;
import org.manage.log.query.util.DefineModelUtil;

import java.util.List;

/**
 * @author cartoon
 * @date 2022/2/19 17:54
 */
@DisplayName("query result builder test")
public class QueryResultBuilderTest extends BaseTest {

    @Order(1)
    @DisplayName("get instance normal test")
    @Test
    public void testGetInstanceNormal(){
        Assertions.assertNotNull(QueryResultBuilder.getInstance());
    }

    @DisplayName("build normal test")
    @Test
    public void testBuildNormal(){
        List<LogRecord> logRecords = ImmutableList.of(DefineModelUtil.defineLogRecord());
        List<QueryLogResp> queryLogRespList = QueryResultBuilder.getInstance().build(logRecords);
        Assertions.assertNotNull(queryLogRespList);
        Assertions.assertEquals(1, queryLogRespList.size());
    }
}
