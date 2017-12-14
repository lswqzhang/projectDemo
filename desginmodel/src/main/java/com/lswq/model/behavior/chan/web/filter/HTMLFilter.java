package com.lswq.model.behavior.chan.web.filter;

import com.lswq.model.behavior.chan.web.bean.Request;
import com.lswq.model.behavior.chan.web.bean.Response;

public class HTMLFilter implements Filter {


    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {

        System.err.println("\n ---HTMLFilter() \n");

        request.requestStr = request.requestStr
                .replace('<', '[')
                .replace('>', ']');
        chain.doFilter(request, response, chain);
        System.err.println("\n ---HTMLFilter() \n");
    }

}
