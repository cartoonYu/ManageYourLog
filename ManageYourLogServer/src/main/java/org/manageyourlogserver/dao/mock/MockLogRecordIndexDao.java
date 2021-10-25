package org.manageyourlogserver.dao.mock;

import com.alibaba.fastjson.JSONArray;
import org.manageyourlogcommon.util.ReadJsonUtil;
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
