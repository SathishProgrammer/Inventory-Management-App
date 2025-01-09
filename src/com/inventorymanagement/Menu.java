package com.inventorymanagement;

import java.util.Scanner;

public class Menu {

	public static void menu() {
		Scanner input = new Scanner(System.in);
		boolean isExit = true;
		
		while(isExit) {
			System.out.println("Inventory Management System");
			
			System.out.println("1: View Products\n"
					+ "2: Add Product\n"
					+ "3: Update Product\n"
					+ "4: Delete Product\n"
					+ "5: Exit");
			
			System.out.println("\nEnter the Option: ");
			int choice = input.nextInt();
			
			switch (choice) {
			
			case 1:
				System.out.println("1: View All Products\n"
						+ "2: View Single Product\n"
						+ "3: View Low Stock Products");
				int choice1 = input.nextInt();
				
				switch (choice1) {
				
				case 1:
					ViewItem.getAllProducts();
					break;
					
				case 2:
					ViewItem.getProduct(input);
					break;
					
				case 3:
					ViewItem.getLowStockProducts();
					break;
				}
				
				break;
				
			case 2:
				AddItem.addNewItem(input);
				break;
				
			case 3:
				UpdateItem.updateProduct(input);
				break;
				
			case 4:
				DeleteItem.deleteProduct(input);
				break;
				
			case 5:
				isExit = false;
				System.out.println("Thank You");
				break;
			}
		}
		input.close();
	}
}
