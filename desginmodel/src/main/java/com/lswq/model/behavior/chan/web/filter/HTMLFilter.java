package com.lswq.model.behavior.chan.web.filter;

import com.lswq.model.behavior.chan.web.bean.Request;
import com.lswq.model.behavior.chan.web.bean.Response;

public class HTMLFilter implements Filter {


    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.requestStr = request.requestStr
                .replace('<', '[')
                .replace('>', ']') + "---HTMLFilter()";
        chain.doFilter(request, response, chain);
        response.responseStr += "---HTMLFilter()";
    }

}
