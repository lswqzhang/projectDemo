package com.lswq.app.listener;

import org.slf4j.MDC;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import java.util.UUID;

/**
 * Created by zhangsw on 2017/1/24.
 */
public class RequestLoggingListener implements ServletRequestListener {

    public void requestInitialized(ServletRequestEvent event) {
        MDC.put("RequestId", String.valueOf(UUID.randomUUID()));
    }

    public void requestDestroyed(ServletRequestEvent event) {
        MDC.clear();
    }
    
}
