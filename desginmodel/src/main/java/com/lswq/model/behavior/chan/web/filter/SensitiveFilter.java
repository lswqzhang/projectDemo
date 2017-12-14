package com.lswq.model.behavior.chan.web.filter;

import com.lswq.model.behavior.chan.web.bean.Request;
import com.lswq.model.behavior.chan.web.bean.Response;

public class SensitiveFilter implements Filter {


    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {

        System.err.println("\n ---SensitiveFilter() \n");

        request.requestStr = request.requestStr
                .replace("被就业", "就业")
                .replace("敏感", "");
        chain.doFilter(request, response, chain);
        System.err.println("\n ---SensitiveFilter() \n");

    }


}
