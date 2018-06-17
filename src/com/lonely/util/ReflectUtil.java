package com.lonely.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReflectUtil {

	/**
	 * 单个map转换成map
	 * 
	 * @param map
	 * @param t
	 * @param isHumpConvert
	 * @return
	 * @throws Exception
	 */
	public static <T> T mapToBean(Map<String, Object> map, T t, boolean isHumpConvert) throws Exception {

		Field[] fields = t.getClass().getDeclaredFields();

		// 是否需要进行驼峰命名转换
		if (isHumpConvert) {
			map = keyConvertByHump(map);
		}

		for (Field field : fields) {

			// 设置该属性可见
			field.setAccessible(true);
			String fieldName = field.getName();

			// 该属性名存在,则赋值
			if (map.get(fieldName) != null) {
				field.set(t, map.get(fieldName));
			}
		}

		// System.out.println(t);

		return t;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T mapToBean(Map<String, Object> map, String className, boolean isHumpConvert) throws Exception {

		Class cls = Class.forName(className);
		Object object = cls.newInstance();
		Field[] fields = object.getClass().getDeclaredFields();
		// 是否需要进行驼峰命名转换
		if (isHumpConvert) {
			map = keyConvertByHump(map);
		}

		for (Field field : fields) {

			// 设置该属性可见
			field.setAccessible(true);
			String fieldName = field.getName();

			// 该属性名存在,则赋值
			if (map.get(fieldName) != null) {
				field.set(object, map.get(fieldName));
			}
		}

		// System.out.println(t);

		return (T) object;
	}

	/**
	 * 将map转换成 baen ，默认不 进行驼峰命名转换
	 * 
	 * @param maps
	 * @param t
	 * @param isHumpConvert
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> mapsToBeans(List<Map<String, Object>> maps, String className) throws Exception {
		List<T> list = new ArrayList<>();
		if (maps != null && !maps.isEmpty()) {
			for (Map<String, Object> map : maps) {
				T bean = mapToBean(map, className, false);
				if (bean != null) {
					list.add(bean);
				}

			}
		}
		return list;
	}

	/**
	 * 将map转换成 bean
	 * 
	 * @param maps
	 * @param t
	 * @param isHumpConvert
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> mapsToBeans(List<Map<String, Object>> maps, String className, boolean isHumpConvert)
			throws Exception {
		List<T> list = new ArrayList<>();
		if (maps != null && !maps.isEmpty()) {
			for (Map<String, Object> map : maps) {
				T bean = mapToBean(map, className, isHumpConvert);
				if (bean != null) {
					list.add(bean);
				}

			}
		}
		return list;
	}

	/**
	 * 将指定map的key经过驼峰命名转换，如 存在 map:{table_name:'test1'} 转换成
	 * map:{tableName:'test1'}
	 * 
	 * @param map
	 * @return
	 */
	private static Map<String, Object> keyConvertByHump(Map<String, Object> map) {
		Map<String, Object> convertMap = new HashMap<>();
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey().toLowerCase();
			Object val = entry.getValue();
			// 下划线转驼峰
			key = StringUtils.underlineToHump(key);
			convertMap.put(key, val);
		}

		return convertMap;
	}

}
