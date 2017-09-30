package com.lswq.model.creater.simplefactory;

import com.lswq.untils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 *
 * 工厂类，用来创造Api对象
 *
 * Created by zhangsw on 2016/12/1.
 */
public abstract class Factory {


    private static final Logger logger = LoggerFactory.getLogger(Factory.class);

    /**
     * 具体的创造Api的方法，根据配置文件的参数来创建接口
     * @return 创造好的Api对象
     */
    public static Api createApi(){
        logger.debug("通过装载工厂配置设置简单工厂方法");
        //直接读取配置文件来获取需要创建实例的类
        //至于如何读取Properties，还有如何反射这里就不解释了
        Properties p = PropertiesUtils.readProperties("/factoryTest.properties");
        //用反射去创建，那些例外处理等完善的工作这里就不做了
        Api api = null;
        try {
            api = (Api) Class.forName(p.getProperty("ImplClass")).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return api;
    }



    /**
     * 具体的创造Api对象的方法
     * @param condition 示意，从外部传入的选择条件
     * @return 创造好的Api对象
     */
    public static Api createApi(int condition){
        //应该根据某些条件去选择究竟创建哪一个具体的实现对象，
        //这些条件可以从外部传入，也可以从其它途径获取。
        //如果只有一个实现，可以省略条件，因为没有选择的必要。

        //示意使用条件
        Api api = null;
        if(condition == 1) {
            api = new ImplA();
        } else if(condition == 2) {
            api = new ImplB();
        }
        return api;
    }
}
