package com.acanblog.openlibrary.back.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

/**
 * 属性文件操作类
 * 
 * @author zheng.can.xin
 * @date 2017年7月11日 上午12:21:37
 */
public class PropertiesUtils {
	public static void main(String[] args) {
		System.out.println(PropertiesUtils.getProperty("jdbc.url"));
	}
	
	/**
	 * 默认属性集合（文件在Constants中配置）
	 */
	protected static Properties defaultProp = null;

	/**
	 * 所有读取过的属性集合 文件名<->属性集合
	 */
	protected static Map<String, Properties> allProps = new HashMap<String, Properties>();

	static {
		if (defaultProp == null) {
			// 注意获取配置文件的路径 包名+文件名
			defaultProp = loadProperties("config.properties");
			allProps.put("config.properties", defaultProp);
		}
	}

	/**
	 * 读取属性文件，并将读出来的属性集合添加到【allProps】当中 如果该属性文件已经读取过，则直接从【allProps】获得
	 * 
	 * @param fileName
	 *            文件名，如果为空，则读物默认属性文件
	 * @return
	 */
	public static Properties getProperties(String fileName) {
		if (fileName == null || "".equals(fileName)) {
			return defaultProp;
		} else {
			Properties prop = allProps.get(fileName);
			if (prop == null) {
				prop = loadProperties(fileName);
				allProps.put(fileName, prop);
			}
			return prop;
		}
	}

	/**
	 * 从指定的属性文件中获取某一属性值 如果属性文件不存在该属性则返回null
	 * 
	 * @param fileName
	 *            文件名
	 * @param name
	 *            key值
	 * @return
	 */
	public static String getProperty(String fileName, String name) {
		return getProperties(fileName).getProperty(name);
	}

	/**
	 * 从默认的属性文件中获取某一属性 如果属性文件不存在则该属性则返回 null
	 * 
	 * @param name
	 * @return
	 */
	public static String getProperty(String name) {
		return getProperties(null).getProperty(name);
	}

	/**
	 * 解析属性文件，将文件中的所有属性都读取到【Properties】当中
	 * 
	 * @param fileName
	 * @return
	 */
	private static Properties loadProperties(String fileName) {
		Properties prop = new Properties();
		InputStream is = null;
		// PropertiesUtils.class.getClassLoader().getResourceAsStream()查找在src文件夹下的文件。
		is = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName);
		if (is == null) {
			System.err.println("Can not find the resource--->"+fileName);
		} else {
			try {
				prop.load(is);
				System.out.println("find the resource--->"+fileName);
			} catch (IOException e) {
				System.err.println("An error occurred when reading from the input stream, " + e.getMessage());
			} catch (IllegalArgumentException e) {
				System.err.println("The input stream contains a malformed Unicode escape sequence, " + e.getMessage());
			} finally {
				IOUtils.closeQuietly(is);
			}
		}
		return prop;
	}

	/**
	 * 取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
	 */
	public Double getDouble(String key) {
		String value = getProperty(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Double.valueOf(value);
	}

	/**
	 * 取出Double类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
	 */
	public Double getDouble(String key, Integer defaultValue) {
		String value = getProperty(key);
		return value != null ? Double.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Boolean类型的Property，但以System的Property优先.如果都为Null抛出异常,如果内容不是true/
	 * false则返回false.
	 */
	public Boolean getBoolean(String key) {
		String value = getProperty(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Boolean.valueOf(value);
	}

	/**
	 * 取出Boolean类型的Property，但以System的Property优先.如果都为Null则返回Default值,如果内容不为true/
	 * false则返回false.
	 */
	public Boolean getBoolean(String key, boolean defaultValue) {
		String value = getProperty(key);
		return value != null ? Boolean.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Integer类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
	 */
	public Integer getInteger(String key, Integer defaultValue) {
		String value = getProperty(key);
		return value != null ? Integer.valueOf(value) : defaultValue;
	}

	/**
	 * 取出Integer类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
	 */
	public Integer getInteger(String key) {
		String value = getProperty(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return Integer.valueOf(value);
	}

}
