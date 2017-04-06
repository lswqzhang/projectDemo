package com.lswq.app.util.spring;

import com.lswq.app.conf.LqConfig;
import com.lswq.app.invoker.LqConfigClientInvoker;
import com.lswq.app.util.AnnotationUtil;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Field;
import java.util.Properties;
import java.util.Set;

/**
 * Created by zhangsw on 2017/4/3.
 */
public class LqConfigClient implements LqConfigClientInvoker {


    private Reflections reflections;
    private Properties props;
    private String scanBasePackage;

    private static final Object lock = new Object();

    @Override
    public void init(Properties props) {
        this.props = props;
        scanAnnotation();
    }

    
    
    public void scanAnnotation() {
        initReflection();
        Set<Field>              fields          = reflections.getFieldsAnnotatedWith(LqConfig.class);
        for (Field f: fields) {
            if (f.isAnnotationPresent(LqConfig.class)) {
                LqConfig    configParam = f.getAnnotation(LqConfig.class);
                String      key         = configParam.key().replace("${", "").replace("}", "");
                watchField(f, key);
            }
        }

    }

    private void watchField(Field f, String key) {
        Class fieldType = f.getDeclaringClass();
        f.setAccessible(true);
        try {
            String value = props.getProperty(key);
            f.set(fieldType, AnnotationUtil.transferValueType(f, value));
        } catch (IllegalAccessException e) {
            
        }

    }


    private void initReflection() {
        if (null == reflections) {
            synchronized (lock) {
                //  double check
                if (null == reflections) {
                    reflections = new Reflections(
                            new ConfigurationBuilder()
                            .setUrls(ClasspathHelper.forPackage(scanBasePackage))
                            .setScanners(new FieldAnnotationsScanner())
                    );
                }
            }
        }
    }

    public void setScanBasePackage(String scanBasePackage) {
        this.scanBasePackage = scanBasePackage;
    }
}
