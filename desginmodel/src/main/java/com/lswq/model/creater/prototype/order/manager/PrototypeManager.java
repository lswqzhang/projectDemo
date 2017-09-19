package com.lswq.model.creater.prototype.order.manager;

import com.lswq.model.creater.prototype.order.proto.OrderApi;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 原型管理器
 */
public abstract class PrototypeManager {
    /**
     * 用来记录原型的编号和原型实例的对应关系
     */
    private static Map<String, OrderApi> map = new ConcurrentHashMap<>();

    /**
     * 向原型管理器里面添加或是修改某个原型注册
     *
     * @param prototypeId 原型编号
     * @param prototype   原型实例
     */
    public synchronized static void setPrototype(String prototypeId, OrderApi prototype) {
        map.put(prototypeId, prototype);
    }

    /**
     * 从原型管理器里面删除某个原型注册
     *
     * @param prototypeId 原型编号
     */
    public synchronized static void removePrototype(String prototypeId) {
        map.remove(prototypeId);
    }

    /**
     * 获取某个原型编号对应的原型实例
     *
     * @param prototypeId 原型编号
     * @return 原型编号对应的原型实例
     * @throws Exception 如果原型编号对应的原型实例不存在，报出例外
     */
    public synchronized static OrderApi getPrototype(String prototypeId) throws Exception {
        OrderApi prototype = map.get(prototypeId);
        if (prototype == null) {
            throw new Exception("您希望获取的原型还没有注册或已被销毁");
        }
        return prototype;
    }

}
