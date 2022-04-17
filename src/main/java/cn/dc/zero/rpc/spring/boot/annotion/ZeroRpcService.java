package cn.dc.zero.rpc.spring.boot.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: DC
 * @Description:
 * @Date: 2022/3/15 15:40
 * @Version: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD, ElementType.PARAMETER})
public @interface ZeroRpcService {

    //服务唯一ID
    String uniqueId() default "";

    //权重
    int weight() default 0;;

    String interfaceId();

    String nodeId() default  "";

    String nodeGroupId() default  "";

    String nodeIdentify() default  "";

    Class<?> interfaceType() default void.class;


}
