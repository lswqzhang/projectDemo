package com.lswq.model.behavior.chan.demo;

import com.lswq.model.behavior.chan.demo.filter.*;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String msg = "大家好:)，<script>，敏感，被就业，网络授课没感觉，因为看不见大家伙儿";
        MsgProcessor mp = new MsgProcessor();
        mp.setMsg(msg);
        
        FilterChain fc = new FilterChain();
        fc.addFilter(new HTMLFilter());
        fc.addFilter(new SensitiveFilter());
        fc.addFilter(new FaceFilter());

        mp.setFc(fc);
        
        String result = mp.process();
        System.out.println(result);
    }

}
