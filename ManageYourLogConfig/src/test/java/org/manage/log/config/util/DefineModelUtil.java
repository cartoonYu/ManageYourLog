package org.manage.log.config.util;

import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.LogConfig;
import org.manage.log.common.util.IdGenerateUtil;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2022/6/5 18:55
 */
public class DefineModelUtil {

    public static LogConfig defineLogConfig(){
        LogConfig logConfig = new LogConfig();
        logConfig.setRuleId(IdGenerateUtil.getInstance().generate(13))
                .setRuleName("test rule" + IdGenerateUtil.getInstance().generate(100))
                .setLogRecordSort(LogRecordSort.DEFAULT)
                .setOperatorSort(OperatorSort.DEFAULT)
                .setIndexSort(LogRecordIndexSort.ID)
                .setDescription("test")
                .setVersion(1L)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return logConfig;
    }
}
