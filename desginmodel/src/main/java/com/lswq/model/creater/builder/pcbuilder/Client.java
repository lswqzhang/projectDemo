package com.lswq.model.creater.builder.pcbuilder;

import com.lswq.model.creater.builder.pcbuilder.concreteproduct.MacBook;
import com.lswq.model.creater.builder.pcbuilder.concreteproduct.SurfacePro;
import com.lswq.untils.PropertiesUtils;

import java.util.Properties;

/**
 * @author zhangshaoweilq@163.com
 * <p>
 * 添加回炉方法的builder的设计模式
 */
public class Client {
    public static void main(String[] args) {
        System.err.println("测试builder设计模式");
        builderTest();
        System.err.println("使用配置文件调用选择实例化对象");
        builderThoughProperties();
    }


    public static void builderThoughProperties() {
        Properties p = PropertiesUtils.readProperties("/builder.properties");
        String implClass = p.getProperty("ImplClass");
        try {
            //  构建
            ComputerProduct.Builder builder = (ComputerProduct.Builder) Class.forName(implClass).newInstance();
            Director director = new Director(builder);
            ComputerProduct macBook = director.buildPC("Inter Board", "Apple Display");
            System.out.println(builder.build().toString());
            //  回炉
            ComputerProduct new_apple_display = macBook.newBuilder().buildDisplay("New Apple Display").build();
            System.out.println(new_apple_display.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void builderTest() {

        ComputerProduct.Builder builder = new MacBook.Builder();
        Director director = new Director(builder);
        ComputerProduct macBook = director.buildPC("Inter Board", "Apple Display");

        ComputerProduct.Builder builder2 = new SurfacePro.Builder();
        director = new Director(builder2);
        ComputerProduct surfacePro = director.buildPC("AMD Board", "LG Display");

        System.out.println(builder.build().toString());
        System.out.println(builder2.build().toString());

        ComputerProduct new_apple_display = macBook.newBuilder().buildDisplay("New Apple Display").build();
        ComputerProduct new_lg_display = surfacePro.newBuilder().buildDisplay("New LG Display").build();

        System.out.println(new_apple_display.toString());
        System.out.println(new_lg_display.toString());
    }
}
