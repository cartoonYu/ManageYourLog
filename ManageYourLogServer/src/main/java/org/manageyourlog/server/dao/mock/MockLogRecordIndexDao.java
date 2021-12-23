package org.manageyourlog.server.dao.mock;

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
        return ReadJsonUtil.readArray("logRecordIndexData.json", LogRecordIndexMockEntity.class);
    }
}
