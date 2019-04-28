package com.wangzhixuan.commons.utils;

import java.io.UnsupportedEncodingException;

import org.springframework.web.util.UriUtils;

/**
 * url处理工具类
 * @author L.cm
 */
public class URLUtils extends org.springframework.web.util.UriUtils {

	/**
	 * url 编码
	 * @param source url
	 * @param encoding 字符集
	 * @return 编码后的url
	 */
	public static String encodeURL(String source, String encoding) {
		try {
			return UriUtils.encode(source, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

}
