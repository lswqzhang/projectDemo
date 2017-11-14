package com.lswq.model.structural.decorator.display.decorator;

import com.lswq.model.structural.decorator.display.component.Component;

public class ComponentDecorator extends Component {
    private Component component;

    public ComponentDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void display() {
        component.display();
    }
}
