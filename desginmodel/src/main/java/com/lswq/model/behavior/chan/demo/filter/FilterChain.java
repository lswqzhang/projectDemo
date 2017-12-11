package com.lswq.model.behavior.chan.demo.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChain implements Filter {

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
