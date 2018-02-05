package com.lswq.model.behavior.chain.ma.demo.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain {

    List<Filter> filters = new ArrayList<>();

    public FilterChain addFilter(Filter f) {
        this.filters.add(f);
        return this;
    }


    public String doFilter(String str) {
        String r = str;
        for (Filter f : filters) {
            r = f.doFilter(r);
        }
        return r;
    }


}
