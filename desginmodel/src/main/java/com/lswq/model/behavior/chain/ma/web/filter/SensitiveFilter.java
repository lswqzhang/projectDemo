package com.lswq.model.behavior.chain.ma.web.filter;

import com.lswq.model.behavior.chain.ma.web.bean.Request;
import com.lswq.model.behavior.chain.ma.web.bean.Response;

public class SensitiveFilter implements Filter {


    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.requestStr = request.requestStr
                .replace("被就业", "就业")
                .replace("敏感", "") + "---SensitiveFilter()";
        chain.doFilter(request, response, chain);
        response.responseStr += "---SensitiveFilter()";

    }


}
