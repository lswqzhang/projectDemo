package com.lswq.model.structural.decorator.display.decorator;

import com.lswq.model.structural.decorator.display.component.Component;

public class BlackBorderDecorator extends ComponentDecorator {

    public BlackBorderDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        this.setBlackBorder();
        super.display();
    }

    public void setBlackBorder() {
        System.err.println("为构建增加黑色边框");
    }
}
