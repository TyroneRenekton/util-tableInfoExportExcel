package com.lonely.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.lonely.pojo.BaseEntity;
import com.lonely.pojo.MySqlExportExcelEntity;
import com.lonely.util.ExportExcel;
import com.lonely.util.JdbcUtil;
import com.lonely.util.ReflectUtil;
import com.lonely.util.SysConstant;

public class MySqlService implements BaseService {

	@Override
	public String prepareConnection(BaseEntity baseEntity) {
		String msg;
		try {
			Class.forName(baseEntity.getDriver());
			Connection connection = DriverManager.getConnection(baseEntity.getUrl(), baseEntity.getUserName(),
					baseEntity.getPassword());
			msg = connection != null ? SysConstant.CONN_SUCCESS : null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			msg = e.getMessage();
		} catch (SQLException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		return msg;
	}

	@Override
	public Connection getConnection(BaseEntity baseEntity) throws SQLException, ClassNotFoundException {
		Class.forName(baseEntity.getDriver());
		Connection connection = DriverManager.getConnection(baseEntity.getUrl(), baseEntity.getUserName(),
				baseEntity.getPassword());
		return connection;
	}

	@Override
	public List<String> showDataBases(Connection conn) throws SQLException {
		List<String> databaseNames = new ArrayList<>();
		String sql = "SHOW DATABASES";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		ResultSet resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			databaseNames.add(resultSet.getString(1));
		}
		return databaseNames;
	}

	@Override
	public boolean exportToExcel(Connection conn, List<String> tableSchemas, List<String> tableNames) {

		String tableSchemaParams = null;
		String tableNameParams = null;

		List<String> params = new ArrayList<>();

		// 编写sql
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT t.`TABLE_NAME`,t.`TABLE_COMMENT`,t.`TABLE_SCHEMA`,c.`COLUMN_NAME`,c.`COLUMN_DEFAULT`,"
				+ "c.`DATA_TYPE` ,c.`COLUMN_TYPE` ,c.`COLUMN_COMMENT` ");
		sql.append(" FROM information_schema.`TABLES` t ");
		sql.append(" INNER JOIN information_schema.COLUMNS c ON t.`TABLE_NAME` = c.`TABLE_NAME` ");
		sql.append(" WHERE 1= 1 ");

		if (tableSchemas != null && !tableSchemas.isEmpty()) {
			params.addAll(tableSchemas);
			sql.append(" AND t.`TABLE_SCHEMA` in (");

			for (int i = 0; i < tableSchemas.size(); i++) {
				sql.append(" ?,");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(" ) ");
			// tableSchemaParams = StringUtils.splicingBy(tableSchemas, false,
			// ",");

		}

		if (tableNames != null && !tableNames.isEmpty()) {
			params.addAll(tableNames);
			sql.append(" AND t.`TABLE_NAME` in ( ");
			for (int i = 0; i < tableNames.size(); i++) {
				sql.append(" ?,");
			}
			sql.deleteCharAt(sql.length() - 1);
			sql.append(" ) ");
			// tableNameParams = StringUtils.splicingBy(tableNames, false, ",");
		}

		// 查询结果

		List<Map<String, Object>> list = JdbcUtil.executeQuery(conn, sql.toString(), params);
		List<MySqlExportExcelEntity> entirys = null;
		try {
			// 查询结果转换成 bean
			entirys = ReflectUtil.mapsToBeans(list, MySqlExportExcelEntity.class.getName(), true);

			// 循环遍历，将不同表进行区分
			Map<String, List<MySqlExportExcelEntity>> maps = new HashMap<>();

			for (MySqlExportExcelEntity mySqlExportExcelEntity : entirys) {
				String tableName = mySqlExportExcelEntity.getTableName();
				if (maps.get(tableName) == null) {
					// Map<String, Object> map = new HashMap<>();
					List<MySqlExportExcelEntity> entityArr = new ArrayList<>();
					entityArr.add(mySqlExportExcelEntity);
					maps.put(tableName, entityArr);

				} else {
					List<MySqlExportExcelEntity> entityArr = maps.get(tableName);
					entityArr.add(mySqlExportExcelEntity);
					maps.put(tableName, entityArr);
				}
			}

			for (Entry<String, List<MySqlExportExcelEntity>> entry : maps.entrySet()) {
				Map<String, Object> map = new HashMap<>();
				map.put("list", entry.getValue());
				map.put("tableName", entry.getKey());
				// ExportExcel.export1("MySqlExportToExcel.ftl", map,
				// "D:/测试1.xlsx",
				// "测试ftl.xls");

				String fileName = entry.getKey() + ".xls";
				ExportExcel.export("MySqlExportToExcel.ftl", map, fileName);
			}

			// 输出
			/*
			 * Map<String, Object> map = new HashMap<>(); map.put("list",
			 * entirys); map.put("tableName", entirys.get(0).getTableName());
			 * ExportExcel.export("MySqlExportToExcel.ftl", map, "测试1.xls");
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
	}
}
