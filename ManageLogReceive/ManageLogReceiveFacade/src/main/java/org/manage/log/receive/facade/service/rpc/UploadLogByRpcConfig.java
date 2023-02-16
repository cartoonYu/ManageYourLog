package org.manage.log.receive.facade.service.rpc;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.facade.config.ApplicationConfigKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * define dubbo with nacos config
 * @author cartoon
 * @date 2022/1/16 17:03
 */
@Configuration
@EnableDubbo
@LoadBean(loadConfigKey = "upload.log.mode", mode = "rpc", needPrimary = false)
public class UploadLogByRpcConfig {

    /**
     * nacos ip
     */
    private String ip;

    /**
     * nacos port
     */
    private Integer port;

    /**
     * indicate client application name
     */
    private static final String APPLICATION_NAME = "ManageLog";

    /**
     * indicate client protocol
     */
    private static final String PROTOCOL_NAME = "dubbo";

    /**
     * indicate client protocol port
     */
    private static final Integer PROTOCOL_PORT = 20882;

    /**
     * indicate client protocol serialization
     */
    private static final String PROTOCOL_SERIALIZATION = "hessian2";

    @Bean("uploadLogByRpcApplicationBean")
    public ApplicationConfig applicationBean() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(APPLICATION_NAME);
        return applicationConfig;
    }

    /**
     * 当前连接注册中心配置
     */
    @Bean("uploadLogByRpcRegistry")
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(String.format("nacos://%s", ip));
        registryConfig.setPort(port);
        return registryConfig;
    }

    @Bean("uploadLogByRpcProtocolConfig")
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(PROTOCOL_NAME);
        protocolConfig.setSerialization(PROTOCOL_SERIALIZATION);
        protocolConfig.setPort(PROTOCOL_PORT);
        return protocolConfig;
    }

    public UploadLogByRpcConfig(ApplicationConfigUtil config) {
        config.get(ApplicationConfigKey.uploadLogUrl.getKey(), (value) -> ip = value);
        config.get(ApplicationConfigKey.uploadLogPort.getKey(), (value) -> port = Integer.parseInt(value));
    }
}
