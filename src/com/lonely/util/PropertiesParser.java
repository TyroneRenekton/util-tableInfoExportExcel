package com.lonely.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 加载 properties文件
 * 
 * @author 15072
 *
 */
public class PropertiesParser extends Properties {

	private PropertiesParser() {

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static PropertiesParser pp;

	// 装载属性文件
	{
		try {
			this.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 定义创建当前类实例的静态的方法
	 */
	public static PropertiesParser newInstance() {
		if (pp == null) {
			pp = new PropertiesParser();
		}

		return pp;
	}

	/**
	 * 定义通过属性文件中对应key获取值得方法
	 */
	public String getValueByKey(String key) {
		return getProperty(key);
	}
}
