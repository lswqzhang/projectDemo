package com.lswq.model.behavior.chain.ma.web.filter;

import com.lswq.model.behavior.chain.ma.web.bean.Request;
import com.lswq.model.behavior.chain.ma.web.bean.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建过虑器管理类，此类也是一个Filter
 *
 * @author zhangsw
 */
public class FilterChain implements Filter {
    List<Filter> filters = new ArrayList<Filter>();
    int index = 0;

    public FilterChain addFilter(Filter f) {
        this.filters.add(f);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {

        System.err.println("\n -- start chain -- \n");


        if (index == filters.size()) return;

        Filter f = filters.get(index);
        index++;
        f.doFilter(request, response, chain);


        System.err.println("\n -- end chain -- \n");
    }
}
