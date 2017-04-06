package com.lswq.app.controller;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.ParameterizedType;

/**
 * Created by zhangsw on 2016/12/29.
 */
public class BaseController<T extends BaseController<T>> {


    private Class<T> subclass;

    protected Logger logger = null;

    @Autowired
    public HttpSession session;

    @Autowired
    public HttpServletRequest request;



    @SuppressWarnings("unchecked")
    public BaseController() {
        subclass = ((Class<T>) ((ParameterizedType) (this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]);
        logger = LoggerFactory.getLogger(subclass);
    }

    /**
     * PostConstruct在构造函数之后执行,init()方法之前执行
     */
    @PostConstruct
    public void init() {
        logger.debug("构造方法初始化执行此方法");
    }

    /**
     * 方法在destroy()方法执行执行之后执行
     */
    @PreDestroy
    public void destroy() {
        logger.debug("");
    }

    @ResponseBody
    @ExceptionHandler
    public Object exception(Exception e) {

        // 添加自己的异常处理逻辑，如日志记录
        logger.error("", e);
        Gson gson = new Gson();

        return gson;
    }


    
    
    
    /**
     * TODO 等到当前访问的地址<br>
     * eg:http://127.0.0.1:8080/
     *
     * @return String
     * @author zhangsw
     */
    public String getBasePath() {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    }

}
