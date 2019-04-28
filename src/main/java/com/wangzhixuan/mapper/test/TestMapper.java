/**
 * 2017-08-26 17:30:56
 */
package com.wangzhixuan.mapper.test;

import org.apache.commons.collections.map.ListOrderedMap;

import java.util.List;
import java.util.Map;

/**
 * @author Esion
 *
 */
public interface TestMapper {
	List<Map<String, Object>> select();
	List<Map<String,Object>> getAllHos();
	List<Map<String,Object>> getAllDept();
}
