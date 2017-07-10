package com.acanblog.openlibrary.back.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具类
 * 
 * @author zheng.can.xin
 * @date 2017年6月25日 下午4:08:51
 */
public class ExceptionUtils {
	/**
	 * 将CheckedException转换为UncheckedException（RuntimeException）
	 * 当在方法内抛出检查性异常时，方法声明也必须向上抛出；而抛出运行时异常则不用 
	 * 用途：一般在catch中向外抛出，使得代码更优雅
	 * 
	 * @param e
	 * @return
	 */
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}
	
	/**
	 * 输出异常栈
	 * @param e
	 * @return
	 */
	public static String getStackTraceAsString(Throwable e) {
		if (e == null){
			return "";
		}
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	
	
	//------------------------------test-----------------------------
	public void checkedException() throws Exception {
		throw new Exception("Generic exception"); // Checked
													// Exception需要显式声明抛出异常或者try
													// catch处理
	}

	public void uncheckedException(String msg) {
		if (msg == null)
			throw new NullPointerException("Msg is null"); // Unchecked
															// Exception语法上不需要处理
	}

}
