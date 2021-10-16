package org.manageyourlogserver.repository;

import com.google.common.collect.ImmutableList;
import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogserver.model.LogRecordIndex;

import java.util.List;

public interface LogRecordRepository {

    default boolean save(LogRecord logRecord){
        return true;
    }

    default List<LogRecord> getByIndex(LogRecordIndex logRecordIndex){
        return ImmutableList.of();
    }

}
