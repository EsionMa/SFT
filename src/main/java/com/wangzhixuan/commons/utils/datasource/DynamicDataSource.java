/**
 * 2017-08-26 16:55:34
 */
package com.wangzhixuan.commons.utils.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author Esion
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/**
	 * 决定使用哪个数据库
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DatabaseContextHolder.getDbType();
	}

}
