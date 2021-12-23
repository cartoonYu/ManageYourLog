package org.manageyourlog.server.dao.mock;

import org.manageyourlog.common.util.ReadJsonUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author cartoon
 * @date 2021/10/24 23:42
 */
@Component
public class MockLogRecordDataDao {

    public List<LogRecordMockEntity> getMockData(){
        return ReadJsonUtil.readArray("logRecordData.json", LogRecordMockEntity.class);
    }
}
