package com.inventorymanagement;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.management.Query;


public class ViewItem {
	public static void getAllProducts() {
		String query = "select * from inventory";
		InventoryJdbcConnection.getConnect();
		try {
			ResultSet result = InventoryJdbcConnection.statement.executeQuery(query);
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
	
	public static void getProduct() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Product Name: ");
		String name = input.nextLine();
		if(getProductName(name) == false) {
			System.out.println("Product not found\n");
		}
		else{
			String query = "select * from inventory where name = ?";
			InventoryJdbcConnection.getConnect();
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
		InventoryJdbcConnection.getConnect();
		int count = 0;
		
		try {
			PreparedStatement ps = InventoryJdbcConnection.connection.prepareStatement(query);
			ResultSet result = ps.executeQuery();
				while(result.next()) {
					System.out.print("ProductId: " + result.getInt(1));
					System.out.print("\nItem name: " + result.getString(2));
					System.out.print("\nCategory: " + result.getString(3));
					System.out.print("\nPrice: ₹" + result.getDouble(4));
					System.out.println("\nStock: " + result.getInt(5) + "\n");
					count++;
				}
				if(count == 0) {
					System.out.println("There is no Product with low stock");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	
	public static boolean getProductName(String name) {
		String query = "select name from inventory where name = ?";
		InventoryJdbcConnection.getConnect();
		try {
			PreparedStatement ps = InventoryJdbcConnection.connection.prepareStatement(query);
			ps.setString(1, name);
			ResultSet result = ps.executeQuery();
	
			if(result.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return false;
	}
}
