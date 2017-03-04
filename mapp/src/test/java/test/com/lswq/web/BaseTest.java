package test.com.lswq.web;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by zhangsw on 2016/12/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class BaseTest {

    static {
        // 通过spring获取配置文件
        Resource resource = new ClassPathResource("conf/logback.xml");

        try {
            //  手动加载配置文件            
            URL url = resource.getURL();
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            configurator.doConfigure(url);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JoranException e) {
            e.printStackTrace();
        }
    }


    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @Autowired
    private WebApplicationContext wac;

    protected MockMvc mockMvc;

    public HttpSession session;


    @Before
    public void setup() throws Exception {

        this.mockMvc = webAppContextSetup(this.wac).build();
        //  模拟用户登录并获取session
        session = this.mockMvc.perform(MockMvcRequestBuilders.post("/system/login").param("mobilePhone", "18801171563").param("password", "12345")).andReturn().getRequest().getSession();

    }


    @Test
    public void test() {
        logger.info("================================");
    }


    /**
     * @deprecated 封装http测试
     * @param url
     * @param param
     * @param method
     * @return
     */
    public String sendHttpUrlTest(String url, Map<String, String> param, RequestMethod method) {
        MockHttpServletRequestBuilder request = null;
        try {
            if (method.equals(RequestMethod.GET)) {
                request = MockMvcRequestBuilders.get(url).session((MockHttpSession) session);
            }
            if (method.equals(RequestMethod.POST)) {
                request = MockMvcRequestBuilders.post(url).session((MockHttpSession) session);
            }
            logger.info("请求地址：{}，请求参数是：{},", url, param);
            if (param != null && !param.isEmpty()) {
                Set<String> keySet = param.keySet();
                for (String string : keySet) {
                    request.param(string, param.get(string));
                }
            }
            ResultActions result = this.mockMvc.perform(request);
            result.andExpect(MockMvcResultMatchers.status().isOk());
            MvcResult andReturn = result.andReturn();

            andReturn.getModelAndView();
            return andReturn.getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{error:\"出现异常了\"}";
        }
    }
}
