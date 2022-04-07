package cn.dc.zero.rpc.spring.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerBootConfig {

    @Value("${zero.rpc.server.proxy:jdk}")
    private String proxy;

    @Value("${zero.rpc.server.balance:random}")
    private String balance;

    public String getProxy() {
        return proxy;
    }

    public void setProxy(String proxy) {
        this.proxy = proxy;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
