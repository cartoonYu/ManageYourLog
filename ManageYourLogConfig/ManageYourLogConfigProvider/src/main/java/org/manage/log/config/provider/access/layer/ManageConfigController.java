package org.manage.log.config.provider.access.layer;

import org.apache.ibatis.annotations.Param;
import org.manage.log.common.model.config.LogConfig;
import org.manage.log.config.facade.dto.LogConfigDto;
import org.manage.log.config.facade.dto.UploadLogConfigDto;
import org.manage.log.config.provider.access.layer.converter.LogConfigConverter;
import org.manage.log.config.provider.service.LogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cartoon
 * @since 2022/11/15 21:27
 */
@RestController
public class ManageConfigController {

    @Autowired
    private LogConfigService logConfigService;

    @PostMapping("/config/add")
    public boolean add(UploadLogConfigDto uploadLogConfigDto){
        LogConfig logConfig = LogConfigConverter.getInstance().convertToBo(uploadLogConfigDto);
        return logConfigService.add(logConfig);
    }

    @GetMapping("/config/getByName")
    public LogConfigDto getByName(@Param("name") String name){
        LogConfig config = logConfigService.getConfigByName(name);
        return LogConfigConverter.getInstance().convertToDto(config);
    }

    @GetMapping("/config/getAll")
    public List<LogConfigDto> getAll(){
        List<LogConfig> configList = logConfigService.getAll();
        return LogConfigConverter.getInstance().convertToDto(configList);
    }
}
