package com.lswq.model.creater.simplefactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhangsw on 2016/12/1.
 */
public class ImplB implements Api {

    private static final Logger logger = LoggerFactory.getLogger(ImplB.class);

    @Override
    public void operation(String s) {
        // 实现功能的代码，示意一下
        logger.info("ImplB s={}", s);
    }
}
