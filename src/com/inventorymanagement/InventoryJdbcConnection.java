package com.inventorymanagement;

import java.sql.*;
import java.lang.Exception;

public class InventoryJdbcConnection {
	static Connection connection;
	static Statement statement;
	public static void getConnect() {
		
		String url = "jdbc:postgresql://localhost:5432/inventorymanagement";
		String userName = "postgres";
		String password = "0000";
		try {
			connection = DriverManager.getConnection(url, userName, password);
			statement = connection.createStatement()
;		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}