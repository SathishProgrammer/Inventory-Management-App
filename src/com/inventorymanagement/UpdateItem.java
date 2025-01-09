package com.inventorymanagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateItem {
	public static void updateProduct(Scanner input) {
		
		System.out.println("Enter the ProductName: ");
		input.nextLine(); // To consume extra line
		String productName = input.nextLine();
		
		System.out.println("Enter the Stock: ");
		int stock = input.nextInt();
		stock += getStock(productName);
		
		String query = "update inventory set stock = ? where name = ?";
		
		try {
			PreparedStatement ps = InventoryJdbcConnection.connection.prepareStatement(query);
			ps.setInt(1, stock);
			ps.setString(2, productName);
			int rowsAffected = ps.executeUpdate();
			System.out.println(rowsAffected + " Row Updated Successfully...");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public static int getStock(String productName) {
		String query = "select stock from inventory where name = ?";
		try {
			
			PreparedStatement ps = InventoryJdbcConnection.connection.prepareStatement(query);
			ps.setString(1, productName);
			ResultSet result = ps.executeQuery();
			result.next();
			return result.getInt("stock");
		} catch (SQLException e) {
			e.printStackTrace();
		}return 0;
	}
}
