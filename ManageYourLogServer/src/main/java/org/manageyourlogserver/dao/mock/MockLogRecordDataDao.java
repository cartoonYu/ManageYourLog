package org.manageyourlogserver.dao.mock;

import com.alibaba.fastjson.JSONArray;
import org.manageyourlogcommon.util.ReadJsonUtil;
import org.manageyourlogserver.model.LogRecord;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/24 23:42
 */
@Component
public class MockLogRecordDataDao {

    public List<LogRecord> getMockData(){
        JSONArray jsonArray = ReadJsonUtil.readJsonArray("logRecordData.json");
        System.out.println(jsonArray);
        return new LinkedList<>();
    }
}
