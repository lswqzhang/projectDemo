package com.lswq.model.structural.decorator.display;

import com.lswq.model.structural.decorator.display.component.Component;
import com.lswq.model.structural.decorator.display.component.Windows;
import com.lswq.model.structural.decorator.display.decorator.BlackBorderDecorator;
import com.lswq.model.structural.decorator.display.decorator.ScrollBarDecorator;

import java.util.concurrent.TimeUnit;

/**
 * 透明的装饰模式，组件实现类尽量是轻类
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        System.out.printf("display scroll bar \n");
        TimeUnit.SECONDS.sleep(1);
        addScrollBar();
        TimeUnit.SECONDS.sleep(1);
        System.out.printf("display black scroll bar\n");
        TimeUnit.SECONDS.sleep(1);
        addBlackScrollBar();
    }

    private static void addBlackScrollBar() {
        Component component, componentSB, componentBB;
        component = new Windows();
        componentSB = new ScrollBarDecorator(component);
        componentBB = new BlackBorderDecorator(componentSB);
        componentBB.display();
    }


    private static void addScrollBar() {
        Component component, componentSB;
        component = new Windows();
        componentSB = new ScrollBarDecorator(component);
        componentSB.display();
    }
}
