package org.manage.log.common.model.log.builder;

import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.log.LogRecordIndex;
import org.manage.log.common.util.IdGenerateUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/19 23:13
 */
@Component
public class LogIndexFactory {

    public String generateIndexId(){
        return IdGenerateUtil.getInstance().generate(13);
    }

    public List<LogRecordIndex> build(String logRecordId, Map<String, LogRecordIndexSort> valueToSortMap){
        List<LogRecordIndex> indexList = new ArrayList<>();
        valueToSortMap.forEach((value, indexSort) -> indexList.add(build(logRecordId, indexSort, value)));
        return indexList;
    }

    public LogRecordIndex build(String logRecordId, LogRecordIndexSort indexSort, String value){
        return LogIndexBuilder.getInstance(this)
                .setLogRecordId(logRecordId)
                .setIndexValue(value)
                .setLogRecordIndexSort(indexSort)
                .build();
    }

    public void check(List<LogRecordIndex> indexList){
        indexList.forEach(this::check);
    }

    public void check(LogRecordIndex logRecordIndex){
        Assert.notNull(logRecordIndex.indexId(), "index id must not be null");
        Assert.notNull(logRecordIndex.logRecordId(), "log record id must not be null");
        Assert.notNull(logRecordIndex.logRecordIndexSort(), "index sort must not be null");
        Assert.notNull(logRecordIndex.indexValue(), "index value must not be null");
        Assert.notNull(logRecordIndex.version(), "version must not be null");
        Assert.notNull(logRecordIndex.createTime(), "create time must not be null");
        Assert.notNull(logRecordIndex.modifyTime(), "modify time must not be null");
    }
}
