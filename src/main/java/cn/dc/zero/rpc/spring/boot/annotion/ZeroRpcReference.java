package cn.dc.zero.rpc.spring.boot.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: DC
 * @Description:
 * @Date: 2022/3/15 12:08
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD, ElementType.PARAMETER})
public @interface ZeroRpcReference {

    //服务唯一ID
    String uniqueId() default "";

    //服务调用方式
    String invokeType() default "sync";

    String interfaceId();

    Class<?> interfaceType() default void.class;
}
