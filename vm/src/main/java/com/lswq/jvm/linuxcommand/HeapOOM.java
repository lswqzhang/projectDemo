package com.lswq.jvm.linuxcommand;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @author zhangsw 
 */
public class HeapOOM {

    static class OOMObject {
        
    }

    public static void main(String[] args) {
        List<OOMObject> list = Lists.newArrayList();
        
        while (true) {
            list.add(new OOMObject());
        }
    }

}
