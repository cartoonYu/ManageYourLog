package org.manage.log.receive.provider.access.layer.builder;

import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.common.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.model.config.builder.LogConfigFactory;
import org.manage.log.common.model.config.builder.LogIndexConfigFactory;
import org.manage.log.receive.facade.dto.config.query.LogConfigDto;
import org.manage.log.receive.facade.dto.config.execute.UploadLogConfigDto;
import org.manage.log.receive.facade.dto.config.query.LogIndexConfigDto;

import java.util.List;
import java.util.Objects;
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
        return logConfigs.stream().filter(Objects::nonNull).map(this::convertToDto).collect(Collectors.toList());
    }

    public LogConfigDto convertToDto(LogConfig logConfig){
        if(Objects.isNull(logConfig)){
            return null;
        }
        LogConfigDto logConfigDto = new LogConfigDto();
        logConfigDto.setRuleName(logConfig.getRuleName())
                    .setLogRecordSort(logConfig.getLogRecordSort().getSortDescription())
                    .setOperatorSort(logConfig.getOperatorSort().getSortDescription())
                    .setIndexConfigList(convertToIndexConfigDto(logConfig.getIndexConfigList()))
                    .setVersion(logConfig.getVersion())
                    .setDescription(logConfig.getDescription())
                    .setCreateTime(logConfig.getCreateTime())
                    .setModifyTime(logConfig.getModifyTime());
        return logConfigDto;
    }

    private List<LogIndexConfigDto> convertToIndexConfigDto(List<LogIndexConfig> logIndexConfigs){
        return logIndexConfigs.stream().map(logConfig -> {
            LogIndexConfigDto logIndexConfigDto = new LogIndexConfigDto();
            logIndexConfigDto.setRuleName(logConfig.getRuleName())
                    .setLogRecordIndexSort(logConfig.getLogRecordIndexSort().getSortDescription())
                    .setValueIndex(logConfig.getValueIndex())
                    .setDescription(logConfig.getDescription())
                    .setVersion(logConfig.getVersion())
                    .setCreateTime(logConfig.getCreateTime())
                    .setModifyTime(logConfig.getModifyTime());
            return logIndexConfigDto;
        }).toList();
    }

    public LogConfig convertToBo(UploadLogConfigDto uploadLogConfigDto){
        List<LogIndexConfig> logIndexConfigList = uploadLogConfigDto.getIndexConfigList().stream().map(indexConfig ->
                LogIndexConfigFactory.getInstance()
                        .build(indexConfig.getRuleName(), LogRecordIndexSort.parse(indexConfig.getLogRecordIndexSort()),
                                indexConfig.getValueIndex(), indexConfig.getDescription())).toList();
        return LogConfigFactory.getInstance()
                .build(uploadLogConfigDto.getRuleName(), LogRecordSort.parse(uploadLogConfigDto.getLogRecordSort()),
                        OperatorSort.parse(uploadLogConfigDto.getOperatorSort()), logIndexConfigList,
                        uploadLogConfigDto.getDescription());
    }

    private LogConfigConverter(){}
}


