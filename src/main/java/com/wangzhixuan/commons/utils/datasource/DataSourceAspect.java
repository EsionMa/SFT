/**
 * 2017-08-27 20:55:58
 */
package com.wangzhixuan.commons.utils.datasource;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @author Esion
 *
 */
public class DataSourceAspect implements MethodBeforeAdvice, AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		DatabaseContextHolder.clearDbType();
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// 判断是否注解数据源类型
		if (method.isAnnotationPresent(CustomDataSource.class)) {
			CustomDataSource customDataSource = method.getAnnotation(CustomDataSource.class);
			DatabaseContextHolder.setDbType(customDataSource.value());
		} else {
			DatabaseContextHolder.setDbType(CustomDataSource.mysql);
		}
	}

}
