package org.manage.log.receive.access.layer.rpc;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.LoadBean;
import org.manage.log.receive.config.ApplicationConfigKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author cartoon
 * @date 2022/1/16 17:03
 */
@Configuration
@DubboComponentScan("org.manage.log.receive.access.layer.rpc")
@LoadBean(loadConfigKey = "receive.log.load.mode", mode = "rpc", defaultClass = ReceiveLogByRpc.class, implementClass = ReceiveLogByRpc.class, needPrimary = false)
public class ReceiveLogByRpcConfig{

    @Autowired
    private ApplicationConfigUtil config;

    private String ip;

    private Integer port;

    private String applicationName;

    private String protocolName;

    private Integer protocolPort;

    private String protocolSerialization;

    @Bean("receiveLogByRpcApplicationBean")
    public ApplicationConfig applicationBean() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
        return applicationConfig;
    }

    /**
     * 当前连接注册中心配置
     */
    @Bean("receiveLogByRpcRegistry")
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(String.format("nacos://%s", ip));
        registryConfig.setPort(port);
        return registryConfig;
    }

    @Bean("receiveLogByRpcProtocolConfig")
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName(protocolName);
        protocolConfig.setSerialization(protocolSerialization);
        protocolConfig.setPort(protocolPort);
        return protocolConfig;
    }

    @PostConstruct
    private void init(){
        config.get(ApplicationConfigKey.receiveLogRpcIp.getKey(), (value) -> ip = value);
        config.get(ApplicationConfigKey.receiveLogRpcPort.getKey(), (value) -> port = Integer.parseInt(value));
        config.get(ApplicationConfigKey.receiveLogRpcApplicationName.getKey(), (value) -> applicationName = value);
        config.get(ApplicationConfigKey.receiveLogRpcProtocolName.getKey(), (value) -> protocolName = value);
        config.get(ApplicationConfigKey.receiveLogRpcProtocolPort.getKey(), (value) -> protocolPort = Integer.parseInt(value));
        config.get(ApplicationConfigKey.receiveLogRpcSerialization.getKey(), (value) -> protocolSerialization = value);
    }
}
