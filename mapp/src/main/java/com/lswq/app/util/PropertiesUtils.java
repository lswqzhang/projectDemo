package com.lswq.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.core.io.ClassPathResource;

/**
 * 
 * 
 * @author zhangsw
 * @version V2.0
 */
public class PropertiesUtils {

	private static PropertiesConfiguration properties = new PropertiesConfiguration();

	private static String[] propertiesFiles = new String[] {
			"params/params.properties", "params/factory.properties",
			"params/redis_keys.properties", "params/data_tree.properties",
			"params/temple.properties", "properties/image_handle.properties",
			"params/ueditor_config.properties" };

	static {
		loadProperties(propertiesFiles);
	}

	private static void loadProperties(String[] propertie) {
		for (String filePath : propertie) {
			try {
				ClassPathResource cpr = new ClassPathResource(filePath);
				InputStream is = cpr.getInputStream();
				BufferedReader bf = new BufferedReader(
						new InputStreamReader(is));
				properties.load(bf);
				bf.close();
				is.close();
			} catch (ConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
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

	public static void main(String[] args) {
		System.err.println(getString("imageActionName"));
	}

}
