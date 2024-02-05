package com.sfb;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/sfb";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
