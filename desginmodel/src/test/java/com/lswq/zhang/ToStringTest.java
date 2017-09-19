package com.lswq.zhang;

import java.util.Map;

public class ToStringTest {
    
    private volatile Map<String, Object> handlerMappings;

    public ToStringTest() {
        
    }

    public static void main(String[] args) {
        ToStringTest test = new ToStringTest();
        Map<String, Object> handlerMappings = test.handlerMappings;
        System.err.println("handlerMappings" + handlerMappings);
    }

    public Map<String, Object> getHandlerMappings() {
        return handlerMappings;
    }

    @Override
    public String toString() {
        return "NamespaceHandlerResolver using mappings " + getHandlerMappings();
    }
}
