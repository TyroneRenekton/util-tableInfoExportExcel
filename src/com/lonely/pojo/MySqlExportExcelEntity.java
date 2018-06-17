package com.lonely.pojo;

/**
 * mysql 导出excel bean
 * 
 * @author 15072
 *
 */
public class MySqlExportExcelEntity {

	/**
	 * 列名
	 */
	private String columnName;

	/**
	 * 列默认值
	 */
	private String columnDefault;

	/**
	 * 类型 比如 varchar，date
	 */
	private String columnDataType;

	/**
	 * 详细类型 ，比如 varchar(32)
	 */
	private String columnType;

	/**
	 * 列 注释
	 */
	private String columnComment;

	/**
	 * 所属表
	 */
	private String tableName;

	/**
	 * 所属数据库
	 */
	private String tableSchema;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnDefault() {
		return columnDefault;
	}

	public void setColumnDefault(String columnDefault) {
		this.columnDefault = columnDefault;
	}

	public String getColumnDataType() {
		return columnDataType;
	}

	public void setColumnDataType(String columnDataType) {
		this.columnDataType = columnDataType;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "MySqlExportExcelEntity [columnName=" + columnName + ", columnDefault=" + columnDefault
				+ ", columnDataType=" + columnDataType + ", columnType=" + columnType + ", columnComment="
				+ columnComment + ", tableName=" + tableName + ", tableSchema=" + tableSchema + "]";
	}

}
