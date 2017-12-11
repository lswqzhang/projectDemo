package com.lswq.model.behavior.chan.web.filter;

import com.lswq.model.behavior.chan.web.bean.Request;
import com.lswq.model.behavior.chan.web.bean.Response;

/**
 * 创建过虑器
 * 
 * @author zhangsw 
 */
public interface Filter {
    void doFilter(Request request, Response response, FilterChain chain);
}
