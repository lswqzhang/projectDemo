package com.lswq.model.behavior.chain.ma.demo.filter;

public class FaceFilter implements Filter {

    @Override
    public String doFilter(String str) {
        return str.replace(":)", "^V^");
    }


}
