package org.manage.log.receive.provider.access.layer.http;

import org.manage.log.common.model.config.LogConfig;
import org.manage.log.receive.facade.dto.config.query.LogConfigDto;
import org.manage.log.receive.facade.dto.config.execute.UploadLogConfigDto;
import org.manage.log.receive.provider.access.layer.builder.LogConfigConverter;
import org.manage.log.receive.provider.service.config.LogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cartoon
 * @since 2022/11/15 21:27
 */
@RestController
@RequestMapping("/config")
public class ManageConfigController {

    @Autowired
    private LogConfigService logConfigService;

    @PostMapping("/add")
    public boolean add(@RequestBody UploadLogConfigDto uploadLogConfigDto){
        LogConfig logConfig = LogConfigConverter.getInstance().convertToBo(uploadLogConfigDto);
        return logConfigService.add(logConfig);
    }

    @GetMapping("/getByName")
    @ResponseBody
    public LogConfigDto getByName(@RequestParam("name") String name){
        return logConfigService.getConfigByName(name)
                .map(config -> LogConfigConverter.getInstance().convertToDto(config))
                .orElse(null);
    }

    @GetMapping("/getAll")
    @ResponseBody
    public List<LogConfigDto> getAll(){
        List<LogConfig> configList = logConfigService.getAll();
        return LogConfigConverter.getInstance().convertToDto(configList);
    }
}
