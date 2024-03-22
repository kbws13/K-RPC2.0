package xyz.kbws.krpc.springboot.starter.annotation;

import org.springframework.context.annotation.Import;
import xyz.kbws.krpc.springboot.starter.bootstrap.RpcConsumerBootstrap;
import xyz.kbws.krpc.springboot.starter.bootstrap.RpcInitBootstrap;
import xyz.kbws.krpc.springboot.starter.bootstrap.RpcProviderBootstrap;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 启用 RPC 注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    /**
     * 需要启动 Server
     * @return
     */
    boolean needServer() default true;
}
