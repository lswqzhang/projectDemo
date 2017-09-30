package com.lswq.untils;

import com.lswq.model.creater.simplefactory.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class PropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    public static Properties readProperties(String filePath) {
        Properties p = new Properties();
        InputStream in = null;
        try {
            in = Factory.class.getResourceAsStream(filePath);
            p.load(in);
        } catch (IOException e) {
            logger.error("装载配置文件出错了，具体的堆栈信息如下：PropertiesUtils#readProperties", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("装载配置文件关闭出错了，具体的堆栈信息如下：PropertiesUtils#readProperties", e);
            }
        }
        return p;
    }
}
