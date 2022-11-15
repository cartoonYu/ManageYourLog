package org.manage.log.config.provider.access.layer.converter;

import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.builder.LogConfigFactory;
import org.manage.log.config.facade.dto.LogConfigDto;
import org.manage.log.config.facade.dto.UploadLogConfigDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @since 2022/11/15 21:03
 */
public class LogConfigConverter {

    private static final LogConfigConverter converter = new LogConfigConverter();

    public static LogConfigConverter getInstance(){
        return converter;
    }

    public List<LogConfigDto> convertToDto(List<LogConfig> logConfigs){
        return logConfigs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public LogConfigDto convertToDto(LogConfig logConfig){
        LogConfigDto logConfigDto = new LogConfigDto();
        logConfigDto.setRuleName(logConfig.getRuleName())
                    .setLogRecordSort(logConfig.getLogRecordSort().getSortDescription())
                    .setOperatorSort(logConfig.getOperatorSort().getSortDescription())
                    .setIndexSort(logConfig.getIndexSort().getSortDescription())
                    .setVersion(logConfig.getVersion())
                    .setCreateTime(logConfig.getCreateTime())
                    .setModifyTime(logConfig.getModifyTime());
        return logConfigDto;
    }

    public LogConfig convertToBo(UploadLogConfigDto uploadLogConfigDto){
        return LogConfigFactory.getInstance()
                .build(uploadLogConfigDto.ruleName(), LogRecordSort.parse(uploadLogConfigDto.logRecordSort()),
                        OperatorSort.parse(uploadLogConfigDto.operatorSort()), LogRecordIndexSort.parse(uploadLogConfigDto.indexSort()),
                        uploadLogConfigDto.description());
    }

    private LogConfigConverter(){}
}


