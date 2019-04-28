/**
 * 2017-08-26 22:20:03
 */
package com.wangzhixuan.commons.utils.datasource;

/**
 * @author Esion
 *
 */
public class DatabaseContextHolder {
	// 线程局部变量（多线程并发设计，为了线程安全）
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	/**
	 * 设置数据库类型
	 * 
	 * @param dbType
	 */
	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}

	/**
	 * 获取数据库类型
	 * 
	 * @return
	 */
	public static String getDbType() {
		return contextHolder.get();
	}

	/**
	 * 清除数据库类型
	 */
	public static void clearDbType() {
		contextHolder.remove();
	}
}
