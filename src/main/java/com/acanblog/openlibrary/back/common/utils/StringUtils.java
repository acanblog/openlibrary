package com.acanblog.openlibrary.back.common.utils;

import java.util.Collection;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


/**
 * 字符串工具类
 * 
 * @author zheng.can.xin
 * @date 2017年6月25日 下午3:25:52
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/**
	 * 打印出bean实例的信息
	 * 
	 * @param object
	 *            bean实例
	 * @return
	 */
	public static String beanString(Object object) {
		return ReflectionToStringBuilder.toString(object);
	}

	/**
	 * 计算给定的字符串的长度，计算规则是：一个汉字的长度为2，一个字符的长度为1
	 *
	 * @param string
	 *            给定的字符串
	 * @return 长度
	 */
	public static int countLength(String string) {
		int length = 0;
		char[] chars = string.toCharArray();
		for (int w = 0; w < string.length(); w++) {
			char ch = chars[w];
			if (ch >= '\u0391' && ch <= '\uFFE5') {
				length++;
				length++;
			} else {
				length++;
			}
		}
		return length;
	}

	/**
	 * 替换为手机识别的HTML，去掉样式及属性，保留回车。
	 * 
	 * @param txt
	 * @return
	 */
	public static String toHtml(String txt) {
		if (txt == null) {
			return "";
		}
		return replace(replace(StringEscapeUtils.escapeHtml4(txt), "\n", "<br/>"), "\t", "&nbsp; &nbsp; ");
	}
	
	
	/**
	 * 转换Collection所有元素(通过toString())为String, 中间以 separator分隔。
	 */
	@SuppressWarnings("rawtypes")
	public static String convertToString(final Collection collection, final String separator) {
		return StringUtils.join(collection, separator);
	}
	
	//----------------------------------------------test
	public static void test(){
		try {
			int a=10/0;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----------------------------------------");
			System.out.println(ExceptionUtils.getStackTraceAsString(e));
			System.out.println("---------------------------------------");
			System.out.println(StringUtils.toHtml(ExceptionUtils.getStackTraceAsString(e)));
		}
	}
	
	public static void main(String[] args) {
		StringUtils.test();
	}
}
