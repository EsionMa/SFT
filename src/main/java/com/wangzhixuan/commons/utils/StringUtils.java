package com.wangzhixuan.commons.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

/**
 * 继承自Spring util的工具类，减少jar依赖
 *
 * @author L.cm
 */
public class StringUtils extends org.springframework.util.StringUtils {
	/**
	 * Check whether the given {@code CharSequence} contains actual
	 * <em>text</em>.
	 * <p>
	 * More specifically, this method returns {@code true} if the
	 * {@code CharSequence} is not {@code null}, its length is greater than 0,
	 * and it contains at least one non-whitespace character.
	 * <p>
	 * <p>
	 * 
	 * <pre class="code">
	 * StringUtils.isBlank(null) = true
	 * StringUtils.isBlank("") = true
	 * StringUtils.isBlank(" ") = true
	 * StringUtils.isBlank("12345") = false
	 * StringUtils.isBlank(" 12345 ") = false
	 * </pre>
	 *
	 * @param cs
	 *            the {@code CharSequence} to check (may be {@code null})
	 * @return {@code true} if the {@code CharSequence} is not {@code null}, its
	 *         length is greater than 0, and it does not contain whitespace only
	 * @see Character#isWhitespace
	 */
	public static boolean isBlank(final CharSequence cs) {
		return !StringUtils.isNotBlank(cs);
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty (""), not null and not whitespace
	 * only.
	 * </p>
	 * <p>
	 * 
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 *
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is not empty and not null and
	 *         not whitespace
	 * @see Character#isWhitespace
	 */
	public static boolean isNotBlank(final CharSequence cs) {
		return org.springframework.util.StringUtils.hasText(cs);
	}

	/**
	 * Convert a {@code Collection} into a delimited {@code String} (e.g. CSV).
	 * <p>
	 * Useful for {@code toString()} implementations.
	 *
	 * @param coll
	 *            the {@code Collection} to convert
	 * @param delim
	 *            the delimiter to use (typically a ",")
	 * @return the delimited {@code String}
	 */
	public static String join(Collection<?> coll, String delim) {
		return org.springframework.util.StringUtils.collectionToDelimitedString(coll, delim);
	}

	/**
	 * Convert a {@code String} array into a delimited {@code String} (e.g.
	 * CSV).
	 * <p>
	 * Useful for {@code toString()} implementations.
	 *
	 * @param arr
	 *            the array to display
	 * @param delim
	 *            the delimiter to use (typically a ",")
	 * @return the delimited {@code String}
	 */
	public static String join(Object[] arr, String delim) {
		return org.springframework.util.StringUtils.arrayToDelimitedString(arr, delim);
	}

	/**
	 * 生成uuid
	 *
	 * @return UUID
	 */
	public static String getUUId() {
		return UUID.randomUUID().toString();
	}

	public static Integer getInt(String intStr) {
		try {
			return Integer.parseInt(intStr);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Long getLong(String intStr) {
		try {
			return Long.parseLong(intStr);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Float getFloat(String intStr) {
		try {
			return Float.parseFloat(intStr);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Double getDouble(String intStr) {
		try {
			return Double.parseDouble(intStr);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public String getStr(Object obj) {
		return obj == null ? "" : obj.toString().trim();
	}

	public static Long getLong(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return new BigDecimal(obj.toString()).longValue();
		} catch (Exception e) {
			return null;
		}
	}
}
