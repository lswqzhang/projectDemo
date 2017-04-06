package com.lswq.app.conf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangsw on 2017/4/3.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LqConfig {


    /**
     * 系统ID
     *
     * @return
     */
    String clientId() default "";


    /**
     * 对应的配置项
     *
     * @return
     */
    String key();
}
