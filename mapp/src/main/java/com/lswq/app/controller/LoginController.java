package com.lswq.app.controller;

import com.lswq.app.util.ResultMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhangsw on 2016/12/29.
 */
@Controller
@RequestMapping("system")
public class LoginController extends BaseController<LoginController> {
    
    @RequestMapping(value = "login")
    public @ResponseBody Object login() {
        logger.info("用户登录测试");
        return ResultMessage.getAppMap(0, "校验成功");
    }
}
