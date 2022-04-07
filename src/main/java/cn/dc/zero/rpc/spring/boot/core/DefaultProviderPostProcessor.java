package cn.dc.zero.rpc.spring.boot.core;


import cn.dc.zero.rpc.core.config.ProviderConfig;
import cn.dc.zero.rpc.core.config.RegistryConfig;
import cn.dc.zero.rpc.core.config.ServerConfig;
import cn.dc.zero.rpc.spring.boot.annotion.ZeroRpcService;
import cn.dc.zero.rpc.spring.boot.config.RegistryBootConfig;
import cn.dc.zero.rpc.spring.boot.config.ServerBootConfig;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DefaultProviderPostProcessor implements ApplicationContextAware {

    @Autowired
    private ServerBootConfig serverBootConfig;

    @Autowired
    private RegistryBootConfig registryBootConfig;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(ZeroRpcService.class);
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                ZeroRpcService rpcService = serviceBean.getClass().getAnnotation(ZeroRpcService.class);
                String uniqueId = rpcService.uniqueId();
                Integer weight = rpcService.weight();
                ProviderConfig providerConfig = new ProviderConfig();
                providerConfig.setInterfaceId(rpcService.interfaceId() == null?serviceBean.getClass().getName():rpcService.interfaceId());
                providerConfig.setUniqueId(uniqueId);
                providerConfig.setRef(serviceBean);
                providerConfig.setWeight(weight);
                List<ServerConfig> serverConfigs = new ArrayList<>();
                serverConfigs.add(serverBootConfig.buildServerConfig());
                providerConfig.setServer(serverConfigs);

                List<RegistryConfig> registryConfigs = new ArrayList<>();
                registryConfigs.add(registryBootConfig.buildProviderRegistryConfig());
                providerConfig.setRegistryConfigs(registryConfigs);
                providerConfig.export();
            }
        }
    }
}
