package com.lonely.pojo;

/**
 * 数据库基础属性
 * 
 * @author 15072
 *
 */
public class BaseEntity {

	/** 驱动 */
	private String driver;

	/** url */
	private String url;

	/** 登陆名 */
	private String userName;

	/** 登陆密码 */
	private String password;

	/** 监听端口 */
	private String port;

	public BaseEntity(String url, String userName, String password) {
		super();
		this.url = url;
		this.userName = userName;
		this.password = password;
	}

	public BaseEntity(String driver, String url, String userName, String password, String port) {
		super();
		this.driver = driver;
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.port = port;
	}

	public BaseEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}
