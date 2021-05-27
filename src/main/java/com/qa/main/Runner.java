package com.qa.main;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Runner {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws SQLException{

		DBConnection dbCon = new DBConnection();
		
		String action;
		action = getAction();
		
		try {
			do {
				switch (action) {
				case "create":
					System.out.println("Please enter your name");
					String name = scan.nextLine();
					dbCon.create(name);
					break;
					
				case "read":
					System.out.println("Displaying Customer Data");
					dbCon.read();
					break;
					
				case "read one":
					System.out.println("Please enter Customer ID");
					int id = scan.nextInt();
					scan.nextLine();//This captures the key we put in
					dbCon.readOne(id);
					break;
					
				case "update":
					System.out.println("Please enter the Customer ID you'd like to update");
					int uId = scan.nextInt();
					scan.nextLine();
					System.out.println("Please enter a new Customer Name");
					String Uname = scan.nextLine();
					dbCon.update(uId, Uname);
					break;
					
				case "delete":
					System.out.println("Please enter the Customer ID you'd like to delete");
					int id2 = scan.nextInt();
					scan.nextLine();
					dbCon.delete(id2);
					break;
				default:
					System.out.println("Sorry, no matches were found!");
					break;
				}
				action = getAction();
				
			} while (!action.equals("exit")); //run the switch case while exit is not selected
			System.out.println("Thank you! Goodbye!");
			
		} finally {
			scan.close();
			dbCon.tearDown();
		}

	}

	private static String getAction() {
		String menuMsg = "==================================================\n" + "IMS System:\n"
				+ "==================================================\n" + "\t- Create\t create a new customer" + "\n"
				+ "\t- Read\t\t Read all customers" + "\n" + "\t- Read one\t Read one customers" + "\n"
				+ "\t- Update\t Update a customer" + "\n" + "\t- Delete\t Delete a customer" + "\n" + "\t=====\n"
				+ "\t- Exit\t\t Exit Application\n" + "==================================================\n";
		System.out.println(menuMsg + "What do you want to do next?");
		return scan.nextLine().toLowerCase();
	}

}
