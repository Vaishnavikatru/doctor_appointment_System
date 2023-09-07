package com.amdocs;

import com.amdocs.Exception.CustomerNotFoundException;
import com.amdocs.dao.impl.CustomerDaoImpl;
import com.amdocs.model.*;

import java.sql.SQLException;
import java.util.*;
public class App 
{
	static CustomerDaoImpl rs=new CustomerDaoImpl();
	static Scanner scan=new Scanner(System.in);
	static AppointmentMenu app= new AppointmentMenu();
    public static void main( String[] args )
    {
			System.out.println("1. Customer");
			System.out.println("2. Doctor");
			System.out.println("3. Appointment");
			int c = Integer.parseInt(scan.nextLine());
			switch (c) {
			case 1:
				CustomerMenu();
				break;
			case 2:
				app.appointmentMenu();
				break;
			case 3:
				app.viewSingleRecord();
			default:
				System.exit(0);
			}

		}
    private static void CustomerMenu()  {
    	System.out.println("1. Register Customer");
    	System.out.println("2. Modify Customer details");
    	System.out.println("3. Delete Customer record");
    	System.out.println("4. View Single record");
    	System.out.println("5. View all records");
    	System.out.println("0. Exit");
    	int c = Integer.parseInt(scan.nextLine());
		switch (c) {
		case 1:
			registration();
			break;
		case 2:
			modifyCustomerDetails();
			break;
		case 3:
			deleteRecord();
			break;
		case 4:
			findSingleRecord();
			break;
		case 5:
			viewAllRecords();
			break;
		default:
			System.exit(0);
		}

    }
 
    
    private static void registration() {
		System.out.println("First Name : " );
		String firstname = scan.nextLine();
		System.out.println("Last Name:" );
		String lastname = scan.nextLine();
		System.out.println("Email Id :");
		String email = scan.nextLine();
		System.out.println("Password:");
		String password = scan.nextLine();
		System.out.println("Confirm Password :");
		String cpassword = scan.nextLine();
		System.out.println("Age:");
		int age = Integer.parseInt(scan.nextLine());
		Customer user=new Customer(firstname,lastname,email,password,age); 
		 if (password.equals(cpassword)) {
			 rs.insert(user);
	         System.out.println("Password and confirmation match. Password set successfully!");
	     } 
		 else {
	            System.out.println("Password and confirmation do not match. Please try again.");
	        }
}
    private static void findSingleRecord() {
    	System.out.println("Enter Emailid:");
    	String emailid=scan.nextLine();
    	Customer find = rs.findById(emailid);
		System.out.println(find.toString());
	}
    
    private static void modifyCustomerDetails()  {
		System.out.println("First Name : " );
		String firstname = scan.nextLine();
		System.out.println("Last Name:" );
		String lastname = scan.nextLine();
		System.out.println("Email Id :");
		String email = scan.nextLine();
		System.out.println("Password:");
		String password = scan.nextLine();
		System.out.println("Age:");
		int age = Integer.parseInt(scan.nextLine());
		Customer ur = new Customer(firstname,lastname,email,password,age);
		rs.update_user(ur);
	
}
    
    private static void viewAllRecords() {
    	try {
			List<Customer> displayAllCustomer = rs.displayAllCustomers();
			for (Customer customer : displayAllCustomer) {
				System.out.println(customer);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		
	}
    
	private static void deleteRecord() {
		System.out.println("Enter a emailid to delete");
		String email=scan.nextLine();
		try {
			rs.removeUser(email);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}