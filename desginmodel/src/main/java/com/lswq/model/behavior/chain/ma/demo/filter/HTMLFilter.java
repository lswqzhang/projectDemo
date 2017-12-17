package com.lswq.model.behavior.chain.ma.demo.filter;

public class HTMLFilter implements Filter {

    @Override
    public String doFilter(String str) {
        //process the html tag <>
        String r = str.replace('<', '[').replace('>', ']');
        return r;
    }


}
