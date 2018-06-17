package com.lonely.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUtil {

	private static String DRIVER_CLASS;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;

	@SuppressWarnings("unused")
	private void initByLoadProperties() {
		DRIVER_CLASS = PropertiesParser.newInstance().getValueByKey("jdbc.driver_class");
		URL = PropertiesParser.newInstance().getValueByKey("jdbc.url");
		USERNAME = PropertiesParser.newInstance().getValueByKey("jdbc.username");
		PASSWORD = PropertiesParser.newInstance().getValueByKey("jdbc.password");

	}

	/**
	 * 定义获取连接的方法
	 */
	private static Connection getConn() {
		Connection conn = null;

		try {
			Class.forName(DRIVER_CLASS);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	/**
	 * 根据 传入的 jdbc信息创建 连接
	 * 
	 * @param driverClass
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public static Connection getConn(String driverClass, String url, String username, String password) {
		Connection conn = null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 定义释放资源的方法
	 * 
	 * @param parameters
	 *            释放参数列表
	 * @return
	 */
	private static void closeObject(Object... parameters) {
		if (parameters != null && parameters.length > 0) {
			try {
				for (Object obj : parameters) {

					if (obj instanceof ResultSet) {
						((ResultSet) obj).close();
					}

					if (obj instanceof Statement) {
						((Statement) obj).close();
					}

					if (obj instanceof Connection) {
						Connection conn = (Connection) obj;

						if (conn != null && !conn.isClosed()) {
							conn.close();
							conn = null; // 释放内存中对象
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 定义设置参数的方法
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	private static void setParameters(PreparedStatement pst, Object... parameters) {
		if (parameters != null && parameters.length > 0) {
			try {
				for (int i = 0; i < parameters.length; i++) {
					pst.setObject(i + 1, parameters[i]);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询指定sql，返回 List , 该方法是 通过load 加载 jdbc.properties来使用
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public static List<Map<String, Object>> executeQuery(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String, Object>> table = null;
		try {

			// 获取连接
			conn = getConn();
			// 创建编译对象
			pst = conn.prepareStatement(sql);
			// 设置参数
			setParameters(pst, parameters);
			// 执行SQL指令并处理返回结果
			rs = pst.executeQuery();
			// 判断结果集是否为空
			if (rs != null) {
				// 把结果集转换为一张虚拟的表
				ResultSetMetaData rsd = rs.getMetaData();
				// 获取当前虚拟表的列数
				int columnCount = rsd.getColumnCount();
				// 创建一个存储每一行的集合对象
				table = new ArrayList<Map<String, Object>>();
				// 遍历行
				while (rs.next()) {
					// 定义存储当前行每一列对应值得Map集合对象
					Map<String, Object> row = new HashMap<String, Object>();
					for (int i = 0; i < columnCount; i++) {
						String columnName = rsd.getColumnName(i + 1);
						String columnValue = rs.getString(columnName);
						// 把列名作为key，当前列对应值作为value存储到row集合中
						row.put(columnName, columnValue);
					}
					// 当前构建行的集合对象存储到存储行的集合中
					table.add(row);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeObject(rs, pst, conn);
		}

		return table;
	}

	/**
	 * 查询指定sql 对应的 返回值, 该方法适用余 前台输入 数据库信息，自己创建conn，传入
	 * 
	 * @param conn
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public static List<Map<String, Object>> executeQuery(Connection conn, String sql, Object... parameters) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String, Object>> table = null;
		try {
			// 创建编译对象
			pst = conn.prepareStatement(sql);
			// 设置参数
			setParameters(pst, parameters);
			// 执行SQL指令并处理返回结果
			rs = pst.executeQuery();
			// 判断结果集是否为空
			if (rs != null) {
				// 把结果集转换为一张虚拟的表
				ResultSetMetaData rsd = rs.getMetaData();
				// 获取当前虚拟表的列数
				int columnCount = rsd.getColumnCount();
				// 创建一个存储每一行的集合对象
				table = new ArrayList<Map<String, Object>>();
				// 遍历行
				while (rs.next()) {
					// 定义存储当前行每一列对应值得Map集合对象
					Map<String, Object> row = new HashMap<String, Object>();
					for (int i = 0; i < columnCount; i++) {
						String columnName = rsd.getColumnName(i + 1);
						String columnValue = rs.getString(columnName);
						// 把列名作为key，当前列对应值作为value存储到row集合中
						row.put(columnName, columnValue);
					}
					// 当前构建行的集合对象存储到存储行的集合中
					table.add(row);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeObject(rs, pst, conn);
		}

		return table;
	}

	/**
	 * 查询指定sql 对应的 返回值, 该方法适用余 前台输入 数据库信息，自己创建conn，传入
	 * 
	 * @param conn
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public static List<Map<String, Object>> executeQuery(Connection conn, String sql, List<String> list) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String, Object>> table = null;
		try {
			// 创建编译对象
			pst = conn.prepareStatement(sql);
			// 设置参数
			// setParameters(pst, parameters);

			if (list != null && !list.isEmpty()) {
				try {
					for (int i = 0; i < list.size(); i++) {
						pst.setObject(i + 1, list.get(i));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// 执行SQL指令并处理返回结果
			rs = pst.executeQuery();
			// 判断结果集是否为空
			if (rs != null) {
				// 把结果集转换为一张虚拟的表
				ResultSetMetaData rsd = rs.getMetaData();
				// 获取当前虚拟表的列数
				int columnCount = rsd.getColumnCount();
				// 创建一个存储每一行的集合对象
				table = new ArrayList<Map<String, Object>>();
				// 遍历行
				while (rs.next()) {
					// 定义存储当前行每一列对应值得Map集合对象
					Map<String, Object> row = new HashMap<String, Object>();
					for (int i = 0; i < columnCount; i++) {
						String columnName = rsd.getColumnName(i + 1);
						String columnValue = rs.getString(columnName);
						// 把列名作为key，当前列对应值作为value存储到row集合中
						row.put(columnName, columnValue);
					}
					// 当前构建行的集合对象存储到存储行的集合中
					table.add(row);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeObject(rs, pst, conn);
		}

		return table;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jdbc.utils.tool.JdbcUtil#executeUpdate(java.lang.String,
	 * java.lang.Object[])
	 */
	/**
	 * 定义执行简单DML操作语句的方法
	 * 
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public static int executeUpdate(String sql, Object... parameters) {
		Connection conn = null;
		PreparedStatement pst = null;
		int row = 0;

		try {

			// 获取连接对象
			conn = getConn();
			// 创建编译对象
			pst = conn.prepareStatement(sql);
			// 调用设置参数的方法
			setParameters(pst, parameters);
			// 执行SQL指令处理返回结果
			row = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 调用释放资源的方法
			closeObject(pst, conn);
		}

		return row;
	}
}
