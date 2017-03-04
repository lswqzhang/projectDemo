package com.lswq.app.util.spring;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by zhangsw on 2016/12/30.
 */
public class PropertiesUtils {
    
    private PropertiesUtils () {
        
    }

    private static PropertiesConfiguration properties = new PropertiesConfiguration();
    
    private static final String FILE_NAMES = "fileNames";

    private static final String FILE_EXT = ".properties";

    private static String[] propertiesFiles = null;

    static {

        try {
            ClassPathResource cpr = new ClassPathResource("conf/propertiesfile.properties");
            InputStream is = cpr.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            PropertiesConfiguration propertiesFile = new PropertiesConfiguration();
            propertiesFile.load(bf);
            bf.close();
            is.close();

            String files = propertiesFile.getString(FILE_NAMES);
            
            propertiesFiles = files.split("\\|");

            loadProperties(propertiesFiles);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }


    }

    private static void loadProperties(String[] propertie) {
        for (String filePath : propertie) {
            try {
                ClassPathResource cpr = new ClassPathResource(filePath + FILE_EXT);
                InputStream is = cpr.getInputStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(is));
                properties.load(bf);
                bf.close();
                is.close();
            } catch (ConfigurationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static ClassLoader classLoader = null;
    private static Class<?> clazz = null;

    public static InputStream getInputStream(String path) throws IOException {
        InputStream is;
        if (clazz != null) {
            is = clazz.getResourceAsStream(path);
        } else if (classLoader != null) {
            is = classLoader.getResourceAsStream(path);
        } else {
            is = ClassLoader.getSystemResourceAsStream(path);
        }
        return is;
    }

    public static String getString(String key) {
        return properties.getString(key);
    }

    public static boolean getBoolean(String key) {
        return properties.getBoolean(key);
    }

    public static double getDouble(String key) {

        return properties.getDouble(key);
    }

    public static int getInt(String key) {
        return properties.getInt(key);
    }

}
