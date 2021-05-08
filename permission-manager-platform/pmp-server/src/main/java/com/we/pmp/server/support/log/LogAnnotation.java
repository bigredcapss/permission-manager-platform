package com.we.pmp.server.support.log;

import java.lang.annotation.*;

/**
 * 自定义日志注解
 * @author we
 * @date 2021-05-07 20:23
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String value() default "";
}
