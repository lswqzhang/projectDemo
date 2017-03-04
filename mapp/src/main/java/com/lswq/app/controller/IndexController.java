package com.lswq.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangsw on 2016/12/30.
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController<IndexController>{
    
    
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView index() {
        
        logger.info("用户访问登录界面, 访问地址：{}", getBasePath());

        ModelAndView mav = new ModelAndView("index");

        mav.addObject("systemName", "系统名称");

        logger.info("用户访问登录界面, 访问地址：{}", getBasePath());

        return mav;
    }

}
