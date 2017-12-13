package com.lswq.model.structural.decorator.prize.decorator;

import com.lswq.model.structural.decorator.prize.component.Component;

import java.util.Date;

/**
 * 
 * 装饰器的接口，需要跟被装饰的对象实现同样的接口
 */
public class Decorator extends Component {
    
    private Component c;

    public Decorator(Component c) {
        this.c = c;
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        return c.calcPrize(user, begin, end);
    }
}
