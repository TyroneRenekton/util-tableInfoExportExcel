package com.lonely.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.lonely.pojo.BaseEntity;

/**
 * 有关服务基类
 * 
 * @author 15072
 *
 */
public interface BaseService {

	/**
	 * 准备连接,即连接测试
	 * 
	 * @param baseEntity
	 * @return
	 */
	String prepareConnection(BaseEntity baseEntity);

	/**
	 * 获取数据库连接
	 * 
	 * @param baseEntity
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Connection getConnection(BaseEntity baseEntity) throws SQLException, ClassNotFoundException;

	/**
	 * 获取指定连接下的所有数据库
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	List<String> showDataBases(Connection conn) throws SQLException;

	/**
	 * 导出指定表信息到excel 输出
	 * 
	 * @param conn
	 * @param tableSchema
	 * @param tableNames
	 * @return
	 */
	boolean exportToExcel(Connection conn, List<String> tableSchemas, List<String> tableNames);

}
