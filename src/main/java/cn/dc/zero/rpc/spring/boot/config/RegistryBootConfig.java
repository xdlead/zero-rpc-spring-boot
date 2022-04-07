package cn.dc.zero.rpc.spring.boot.config;


import cn.dc.zero.rpc.core.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistryBootConfig {

    @Value("${zero.rpc.provider.registry.address:127.0.0.1}")
    private String providerAddress;

    @Value("${zero.rpc..provider.registry.protocol:zookeeper}")
    private String providerProtocol;

    @Value("${zero.rpc..provider.registry.connectTimeout:10000}")
    private int providerConnectTimeout;

    @Value("${zero.rpc.consumer.registry.address:127.0.0.1}")
    private String consumerAddress;

    @Value("${zero.rpc.consumer.registry.protocol:zookeeper}")
    private String consumerProtocol;

    @Value("${zero.rpc.consumer.registry.connectTimeout:10000}")
    private int consumerConnectTimeout;

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress;
    }

    public String getProviderProtocol() {
        return providerProtocol;
    }

    public void setProviderProtocol(String providerProtocol) {
        this.providerProtocol = providerProtocol;
    }

    public int getProviderConnectTimeout() {
        return providerConnectTimeout;
    }

    public void setProviderConnectTimeout(int providerConnectTimeout) {
        this.providerConnectTimeout = providerConnectTimeout;
    }

    public String getConsumerAddress() {
        return consumerAddress;
    }

    public void setConsumerAddress(String consumerAddress) {
        this.consumerAddress = consumerAddress;
    }

    public String getConsumerProtocol() {
        return consumerProtocol;
    }

    public void setConsumerProtocol(String consumerProtocol) {
        this.consumerProtocol = consumerProtocol;
    }

    public int getConsumerConnectTimeout() {
        return consumerConnectTimeout;
    }

    public void setConsumerConnectTimeout(int consumerConnectTimeout) {
        this.consumerConnectTimeout = consumerConnectTimeout;
    }

    public RegistryConfig buildProviderRegistryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(providerAddress);
        registryConfig.setConnectTimeout(providerConnectTimeout);
        registryConfig.setProtocol(providerProtocol);
        return registryConfig;
    }

    public RegistryConfig buildConsumerRegistryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(consumerAddress);
        registryConfig.setConnectTimeout(consumerConnectTimeout);
        registryConfig.setProtocol(consumerProtocol);
        return registryConfig;
    }
}
