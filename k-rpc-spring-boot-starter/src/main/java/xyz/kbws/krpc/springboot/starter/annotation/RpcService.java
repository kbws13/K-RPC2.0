package xyz.kbws.krpc.springboot.starter.annotation;

import org.springframework.stereotype.Component;
import xyz.kbws.rpc.constants.RpcConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 服务提供者注解（用于注册服务）
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {
    /**
     * 服务接口类
     */
    Class<?> interfaceClass() default void.class;

    /**
     * 版本
     */
    String serviceVersion() default RpcConstants.DEFAULT_SERVICE_VERSION;
}
