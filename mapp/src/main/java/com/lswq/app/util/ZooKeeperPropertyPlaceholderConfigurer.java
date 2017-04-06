package com.lswq.app.util;

import com.lswq.app.util.spring.LqConfigClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.List;
import java.util.Properties;

/**
 * Created by zhangsw on 2017/4/2.
 */
public class ZooKeeperPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    private String scanBasePackage;
    private LqConfigClient client;
    private Properties props;
    private ZookeeperClientUtil configurationClient;


    private void registryLqConfClient() {
        this.client = new LqConfigClient();
        this.client.setScanBasePackage(scanBasePackage);
        this.client.init(props);
        this.configurationClient.setProps(props);
        this.configurationClient.setClient(client);
    }


    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        try {
            List<String> list = configurationClient.getChildren();
            for (String key : list) {
                String value = configurationClient.getData(configurationClient.getMainPath() + "/" + key);
                if (!StringUtils.isBlank(value)) {
                    props.put(key, value);
                }
            }
            this.props = props;
            registryLqConfClient();
        } catch (Exception e) {

        }
        super.processProperties(beanFactoryToProcess, props);
    }

    public void setScanBasePackage(String scanBasePackage) {
        this.scanBasePackage = scanBasePackage;
    }


    public void setConfigurationClient(ZookeeperClientUtil configurationClient) {
        this.configurationClient = configurationClient;
    }
}
