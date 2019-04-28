/**
 * 2017-08-27 17:26:57
 */
package com.wangzhixuan.commons.utils.datasource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Esion
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomDataSource {
	String value();

	/** Mysql数据库 */
	String mysql = "dataSource1";
	/** Oracle数据库 */
	String oracle = "dataSource2";
	/** Sql Server数据库 */
	String sqlserver = "dataSource3";

}
