package com.lonely.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 * 
 * @author 15072
 *
 */
public class StringUtils {

	/**
	 * 返回指定字符串是否为null或者""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 将list根据指定连接符，进行 拼接
	 * 
	 * @param list
	 * @param isStringType
	 * @param separator
	 * @return
	 */
	public static String splicingBy(List<String> list, boolean isStringType, String separator) {

		StringBuilder str = new StringBuilder();

		for (String s : list) {
			// 判断是否是string类型，如果是string类型，需要使用 ''包括
			if (isStringType) {
				str.append("'" + s + "'" + separator);
			} else {
				str.append(s + separator);
			}
		}

		// 去掉最后的 分割符号
		int lastIndexOf = str.lastIndexOf(separator);
		if (lastIndexOf == -1) {
			return str.toString();
		} else {
			return str.substring(0, lastIndexOf);
		}
	}

	/**
	 * 将下划线 str 转换成 驼峰命名规则的str
	 * 
	 * @param para
	 * @return
	 */
	public static String underlineToHump(String para) {
		StringBuilder result = new StringBuilder();
		String a[] = para.split("_");
		for (String s : a) {
			if (result.length() == 0) {
				result.append(s.toLowerCase());
			} else {
				result.append(s.substring(0, 1).toUpperCase());
				result.append(s.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

	/**
	 * 驼峰转下划线
	 * 
	 * @param str
	 * @return
	 */
	public static String humpToLine(String str) {
		Pattern humpPattern = Pattern.compile("[A-Z]");
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static void main(String[] args) {
	}
}
