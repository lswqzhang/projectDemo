package com.lswq.model.behavior.chain.ma.demo.filter;

/**
 * 消息处理过虑器
 *
 * @author zhangsw
 */
public class MsgProcessor {

    private String msg;

    FilterChain fc;

    public FilterChain getFc() {
        return fc;
    }

    public void setFc(FilterChain fc) {
        this.fc = fc;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String process() {

        return fc.doFilter(msg);

    }

}
