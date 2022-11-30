package org.manage.log.config.provider.access.layer.rpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.manage.log.config.facade.LogConfigFacade;
import org.manage.log.config.facade.dto.LogConfigDto;
import org.manage.log.config.provider.access.layer.converter.LogConfigConverter;
import org.manage.log.config.provider.service.LogConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/11/28 21:18
 */
@Service
@DubboService
public class LogConfigServiceProvider implements LogConfigFacade {

    @Autowired
    private LogConfigService logConfigService;

    @Override
    public LogConfigDto getByName(String configName) {
        return LogConfigConverter.getInstance().convertToDto(logConfigService.getConfigByName(configName));
    }
}
