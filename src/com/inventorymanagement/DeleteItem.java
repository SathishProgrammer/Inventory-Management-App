package com.inventorymanagement;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteItem {
	public static void deleteProduct() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Product name: ");
		String name = input.nextLine();
		
		if(ViewItem.getProductName(name) == false) {
			System.out.println("Product not found\n");
		}
		else {
			String query = "delete from inventory where name = ?";
			
			try {
				PreparedStatement ps = InventoryJdbcConnection.connection.prepareStatement(query);
				ps.setString(1, name);
				ps.execute();
				System.out.println("Deleted Successfully...");
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}
}
