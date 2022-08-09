package org.manage.log.upload.service.rpc;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.upload.UploadLog;
import org.manage.log.upload.config.ApplicationConfigKey;
import org.manage.log.upload.service.UploadLogByDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * define dubbo with nacos config
 * @author cartoon
 * @date 2022/1/16 17:03
 */
@Configuration
@EnableDubbo
@LoadBean(loadConfigKey = "upload.log.mode", mode = "rpc", defaultClass = UploadLogByDefault.class, implementClass = UploadLog.class, needPrimary = false)
public class UploadLogByRpcConfig {

    @Autowired
    private ApplicationConfigUtil config;

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
    private String applicationName;

    /**
     * indicate client protocol
     */
    private String protocolName;

    /**
     * indicate client protocol port
     */
    private Integer protocolPort;

    /**
     * indicate client protocol serialization
     */
    private String protocolSerialization;

    @Bean("uploadLogByRpcApplicationBean")
    public ApplicationConfig applicationBean() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
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
        protocolConfig.setName(protocolName);
        protocolConfig.setSerialization(protocolSerialization);
        protocolConfig.setPort(protocolPort);
        return protocolConfig;
    }

    @PostConstruct
    private void init(){
        config.get(ApplicationConfigKey.uploadLogRpcIp.getKey(), (value) -> ip = value);
        config.get(ApplicationConfigKey.uploadLogRpcPort.getKey(), (value) -> port = Integer.parseInt(value));
        config.get(ApplicationConfigKey.uploadLogRpcApplicationName.getKey(), (value) -> applicationName = value);
        config.get(ApplicationConfigKey.uploadLogRpcProtocolName.getKey(), (value) -> protocolName = value);
        config.get(ApplicationConfigKey.uploadLogRpcProtocolPort.getKey(), (value) -> protocolPort = Integer.parseInt(value));
        config.get(ApplicationConfigKey.uploadLogRpcSerialization.getKey(), (value) -> protocolSerialization = value);
    }
}
