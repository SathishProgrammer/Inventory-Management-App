package com.inventorymanagement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddItem {
	public static void addNewItem(Scanner input) {
		
		System.out.println("Enter the Id: ");
		int productId = input.nextInt();
		
		input.nextLine(); // To consume extra line
		System.out.println("Enter the Product name: ");
		String productName = input.nextLine();
		
		System.out.println("Enter the Category: ");
		String category = input.nextLine();
		
		System.out.println("Enter the Price: ");
		double price = input.nextDouble();
		
		System.out.println("Enter the Stock: ");
		int stock = input.nextInt();
		
		String query = "insert into inventory values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement preparedStatement = InventoryJdbcConnection.connection.prepareStatement(query);
			preparedStatement.setInt(1, productId);
			preparedStatement.setString(2, productName);
			preparedStatement.setString(3, category);
			preparedStatement.setDouble(4, price);
			preparedStatement.setInt(5, stock);
			
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " Row Added Succesfully...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
