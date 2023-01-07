package org.manage.log.receive.provider.access.layer.builder;

import org.manage.log.common.model.log.constants.LogRecordIndexSort;
import org.manage.log.common.model.log.constants.LogRecordSort;
import org.manage.log.common.model.log.constants.OperatorSort;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.common.model.config.LogIndexConfig;
import org.manage.log.common.model.config.builder.LogConfigFactory;
import org.manage.log.common.model.config.builder.LogIndexConfigFactory;
import org.manage.log.receive.facade.dto.config.query.LogConfigDto;
import org.manage.log.receive.facade.dto.config.execute.UploadLogConfigDto;
import org.manage.log.receive.facade.dto.config.query.LogIndexConfigDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @since 2022/11/15 21:03
 */
@Component
public class LogConfigConverter {

    private final LogConfigFactory logConfigFactory;

    private final LogIndexConfigFactory logIndexConfigFactory;

    public List<LogConfigDto> convertToDto(List<LogConfig> logConfigs){
        return logConfigs.stream().filter(Objects::nonNull).map(this::convertToDto).collect(Collectors.toList());
    }

    public LogConfigDto convertToDto(LogConfig logConfig){
        if(Objects.isNull(logConfig)){
            return null;
        }
        LogConfigDto logConfigDto = new LogConfigDto();
        logConfigDto.setRuleName(logConfig.ruleName())
                    .setLogRecordSort(logConfig.logRecordSort().getSortDescription())
                    .setOperatorSort(logConfig.operatorSort().getSortDescription())
                    .setIndexConfigList(convertToIndexConfigDto(logConfig.indexConfigList()))
                    .setVersion(logConfig.version())
                    .setDescription(logConfig.description())
                    .setCreateTime(logConfig.createTime())
                    .setModifyTime(logConfig.modifyTime());
        return logConfigDto;
    }

    private List<LogIndexConfigDto> convertToIndexConfigDto(List<LogIndexConfig> logIndexConfigs){
        return logIndexConfigs.stream().map(logConfig -> {
            LogIndexConfigDto logIndexConfigDto = new LogIndexConfigDto();
            logIndexConfigDto.setRuleName(logConfig.ruleName())
                    .setLogRecordIndexSort(logConfig.logRecordIndexSort().getSortDescription())
                    .setValueIndexKey(logConfig.valueIndexKey())
                    .setDescription(logConfig.description())
                    .setVersion(logConfig.version())
                    .setCreateTime(logConfig.createTime())
                    .setModifyTime(logConfig.modifyTime());
            return logIndexConfigDto;
        }).toList();
    }

    public LogConfig convertToBo(UploadLogConfigDto uploadLogConfigDto){
        List<LogIndexConfig> logIndexConfigList = new ArrayList<>();
        if(Objects.nonNull(uploadLogConfigDto.getIndexConfigList())){
            logIndexConfigList.addAll(uploadLogConfigDto.getIndexConfigList().stream().map(indexConfig ->
                    logIndexConfigFactory
                            .build(indexConfig.getRuleName(), LogRecordIndexSort.parse(indexConfig.getLogRecordIndexSort()),
                                    indexConfig.getValueIndexKey(), indexConfig.getDescription())).toList());
        }
        return logConfigFactory
                .build(uploadLogConfigDto.getRuleName(), LogRecordSort.parse(uploadLogConfigDto.getLogRecordSort()),
                        OperatorSort.parse(uploadLogConfigDto.getOperatorSort()), uploadLogConfigDto.getContentTemplate(), logIndexConfigList,
                        uploadLogConfigDto.getDescription());
    }

    public LogConfigConverter(LogConfigFactory logConfigFactory,
                              LogIndexConfigFactory logIndexConfigFactory) {
        this.logConfigFactory = logConfigFactory;
        this.logIndexConfigFactory = logIndexConfigFactory;
    }
}


