package xyz.kbws.krpc.springboot.starter.annotation;

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
public @interface EnableRpc {

    /**
     * 需要启动 Server
     * @return
     */
    boolean needServer() default true;
}
