package org.manageyourlog.server.mock;

import com.alibaba.fastjson.JSONArray;
import org.manageyourlog.common.util.ReadJsonUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/24 23:42
 */
@Repository
public class MockLogRecordIndexDao {

    public List<LogRecordIndexMockEntity> getMockData(){
        JSONArray jsonArray = ReadJsonUtil.readJsonArray("logRecordIndexData.json");
        List<LogRecordIndexMockEntity> res = jsonArray.toJavaList(LogRecordIndexMockEntity.class);
        return res;
    }
}
