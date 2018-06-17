package com.lonely.pojo;

public class MySqlEntity extends BaseEntity {

	public MySqlEntity() {
		this.init();
	}

	private void init() {
		super.setDriver("com.mysql.cj.jdbc.Driver");
		if (super.getPort() == null || super.getPort().equals("")) {
			super.setPort("3306");
		}
	}

	public MySqlEntity(String url, String userName, String password) {
		super(url, userName, password);
		this.init();
	}

}
