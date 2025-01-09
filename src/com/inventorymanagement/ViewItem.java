package com.inventorymanagement;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class ViewItem {
	
	public static void getAllProducts() {
		String query = "select * from inventory";
		InventoryJdbcConnection.getConnection();
		System.out.println("+------------+--------------------+----------------------+-------------+--------+");
		System.out.println("| Product Id | Name               | Category             | Price       | Stock  |");
		System.out.println("+------------+--------------------+----------------------+-------------+--------+");
		try {
			ResultSet result = InventoryJdbcConnection.statement.executeQuery(query);
			while(result.next()) {
				int productId = result.getInt(1);
				String name = result.getString(2);
				String category = result.getString(3);
				double price = result.getDouble(4);
				int stock = result.getInt(5);
				System.out.printf("| %-10s | %-18s | %-20s | ₹%-10s | %-6s |\n", productId, name, category, price, stock);
				System.out.println("+------------+--------------------+----------------------+-------------+--------+");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void getProduct(Scanner input) {
		System.out.println("Enter the Product Name: ");
		input.nextLine(); // To consume extra line
		String name = input.nextLine();
		if(getProductName(name) == false) {
			System.out.println("Product not found\n");
		}
		else{
			String query = "select * from inventory where name = ?";
			try {
				PreparedStatement ps = InventoryJdbcConnection.connection.prepareStatement(query);
				ps.setString(1, name);
				ResultSet result = ps.executeQuery();
				while(result.next()) {
					System.out.print("ProductId: " + result.getInt(1));
					System.out.print("\nItem name: " + result.getString(2));
					System.out.print("\nCategory: " + result.getString(3));
					System.out.print("\nPrice: ₹" + result.getDouble(4));
					System.out.println("\nStock: " + result.getInt(5) + "\n");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static void getLowStockProducts() {
		String query = "select * from inventory where stock < 10";
		
		try {
			PreparedStatement ps = InventoryJdbcConnection.connection.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			if(!result.isBeforeFirst()) {
				System.out.println("There is no Product with low stock");
			}
			else {
				System.out.println("Products with lowest stocks:");
				while(result.next()) {
					System.out.print("ProductId: " + result.getInt(1));
					System.out.print("\nItem name: " + result.getString(2));
					System.out.print("\nCategory: " + result.getString(3));
					System.out.print("\nPrice: ₹" + result.getDouble(4));
					System.out.println("\nStock: " + result.getInt(5) + "\n");
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	
	public static boolean getProductName(String name) {
		String query = "select name from inventory where name = ?";
		try {
			PreparedStatement ps = InventoryJdbcConnection.connection.prepareStatement(query);
			ps.setString(1, name);
			ResultSet result = ps.executeQuery();
	
			if(result.isBeforeFirst()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return false;
	}
}
