package cn.dc.zero.rpc.spring.boot.core;


import cn.dc.zero.rpc.core.config.ConsumerConfig;
import cn.dc.zero.rpc.core.config.RegistryConfig;
import cn.dc.zero.rpc.core.util.StringUtils;
import cn.dc.zero.rpc.spring.boot.annotion.ZeroRpcReference;
import cn.dc.zero.rpc.spring.boot.config.ConsumerBootConfig;
import cn.dc.zero.rpc.spring.boot.config.RegistryBootConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultConsumerPostProcessor implements BeanPostProcessor {

    @Autowired
    private ConsumerBootConfig consumerBootConfig;

    @Autowired
    private RegistryBootConfig registryBootConfig;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class clazz = bean.getClass();
        processFields(bean, clazz.getDeclaredFields());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private void processFields(Object bean, Field[] declaredFields) {
        for (Field field : declaredFields) {
            ZeroRpcReference reference = AnnotationUtils.getAnnotation(field, ZeroRpcReference.class);
            if (reference == null) {
                continue;
            }
            ConsumerConfig consumerConfig = new ConsumerConfig();

            consumerConfig.setProxy(consumerBootConfig.getProxy());
            if(StringUtils.isBlank(reference.interfaceId())){
                consumerConfig.setInterfaceId(reference.interfaceType().getTypeName());
            }else{
                consumerConfig.setInterfaceId(reference.interfaceId());
            }
            consumerConfig.setUniqueId(StringUtils.isBlank(reference.uniqueId()) ?consumerConfig.getInterfaceId():reference.uniqueId());
            consumerConfig.setInvokeType(reference.invokeType());

            consumerConfig.setBalancer(consumerBootConfig.getBalance());
            List<RegistryConfig> registryConfigs = new ArrayList<>();
            registryConfigs.add(registryBootConfig.buildProviderRegistryConfig());
            consumerConfig.setRegistryConfigs(registryConfigs);
            Object proxy = consumerConfig.refer();
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, bean, proxy);
        }
    }

}
