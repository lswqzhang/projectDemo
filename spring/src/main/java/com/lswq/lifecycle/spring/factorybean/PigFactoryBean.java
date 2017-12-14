package com.lswq.lifecycle.spring.factorybean;

import com.lswq.lifecycle.spring.bean.Pig;
import org.springframework.beans.factory.FactoryBean;

/**
 * 小猪猪的生成器
 * <p>
 * 希望通过该通用bean产生一个我们希望的bean
 *
 * @author zhangshaowei
 */
public class PigFactoryBean implements FactoryBean<Pig> {

    private String pigInfo;

    @Override
    public Pig getObject() throws Exception {
        Pig pig = new Pig();
        String[] info = pigInfo.split(",");
        pig.setName(info[0]);
        pig.setAge(Integer.parseInt(info[1]));
        return pig;
    }

    @Override
    public Class<Pig> getObjectType() {
        return Pig.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getPigInfo() {
        return pigInfo;
    }

    public void setPigInfo(String pigInfo) {
        this.pigInfo = pigInfo;
    }
}
