package com.lswq.model.structural.decorator.prize.decorator;

import com.lswq.model.structural.decorator.prize.component.Component;
import com.lswq.model.structural.decorator.prize.data.TempDB;

import java.util.Date;

public class MonthPrizeDecorator extends Decorator {
    public MonthPrizeDecorator(Component c) {
        super(c);
    }

    @Override
    public double calcPrize(String user, Date begin, Date end) {
        //1：先获取前面运算出来的奖金  
        double money = super.calcPrize(user, begin, end);
        //2：然后计算当月业务奖金,按人员和时间去获取当月业务额，然后再乘以3%  
        double prize = TempDB.mapMonthSaleMoney.get(user) * 0.03;
        System.out.println(user + "当月业务奖金" + prize);
        return money + prize;
    }
}
