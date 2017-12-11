package com.lswq.javaio.rpcdemo.rpc.handler;

import com.lswq.javaio.rpcdemo.rpc.bean.RpcCommand;
import com.lswq.javaio.rpcdemo.rpc.bean.RpcResponse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class RpcHandler {
    ConcurrentHashMap<String, Object> registered = new ConcurrentHashMap<>(128);

    public RpcResponse handler(RpcCommand commond) {
        String className = commond.getClassName();
        RpcResponse response = new RpcResponse();
        try {
            Object obj = registered.get(className);
            String[] argTypes = commond.getArgumetsType();
            Class aClass = Class.forName(className);
            List<Class> argsTypeList = new ArrayList<Class>(argTypes.length);
            for (String s : argTypes) {
                argsTypeList.add(Class.forName(s));
            }
            Method method = aClass.getMethod(commond.getMethodName(), argsTypeList.toArray(new Class[argsTypeList.size()]));
            Object object = method.invoke(obj, commond.getParams());
            response.setResult(object);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(true);
            response.setException(e);
        }
        return response;
    }

    public void register(Class interfa, Object object) {
        registered.put(interfa.getName(), object);
    }
}
