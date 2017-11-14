package com.lswq.model.structural.decorator.prize.decorator;

import com.lswq.model.structural.decorator.prize.component.Component;
import com.lswq.model.structural.decorator.prize.data.TempDB;

import java.util.Date;

public class GroupPrizeDecorator extends Decorator {

    public GroupPrizeDecorator(Component c) {
        super(c);
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        //1：先获取前面运算出来的奖金  
        double money = super.calcPrize(user, begin, end);
        //2：然后计算当月团队业务奖金，先计算出团队总的业务额，然后再乘以1%  
        //假设都是一个团队的  
        double group = 0.0;
        for (double d : TempDB.mapMonthSaleMoney.values()) {
            group += d;
        }
        double prize = group * 0.01;
        System.out.println(user + "当月团队业务奖金" + prize);
        return money + prize;
    }
}
