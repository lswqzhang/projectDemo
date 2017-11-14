package com.lswq.model.structural.decorator.display.decorator;

import com.lswq.model.structural.decorator.display.component.Component;

public class ScrollBarDecorator extends ComponentDecorator {

    public ScrollBarDecorator(Component component) {
        super(component);
    }

    @Override
    public void display() {
        this.setScrollBar();
        super.display();
    }
    
    public void setScrollBar() {
        System.err.println("为构建增加滚动条");
    }
}
