package com.testJDBC2;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class App {
	
	private static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		try {
			App.generateMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
	}
	
	private static void generateMenu() throws Exception {
		System.out.println("Choose an option: ");
		System.out.println("1.Add User ");
		System.out.println("2.Update User ");
		System.out.println("3.Delete User ");
		System.out.println("4.Get Employee By Id ");
		System.out.println("5.Get All Employees ");
		System.out.println("6.Exit ");
		
		int x = s.nextInt();
		
		switch(x) {
		case 1: App.addUser(); break;
		case 2: App.updateUser(); break;
		case 3: App.deleteUser(); break;
		case 4: App.getUserById(); break;
		case 5: App.getAllUsers(); break;
		case 6: App.exit(); break;
		default: System.out.println("Wrong Option selected."); break;
		}
		
		App.generateMenu();
	}
	
	private static void addUser() throws Exception {
		User user = new User();
		System.out.println("User Id: ");
		user.setId(s.nextInt());
		System.out.println("User Name: ");
		user.setName(s.next());
		System.out.println("User DOB(dd-MM-yyyy): ");
		user.setDob(new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(s.next()).getTime()));
		System.out.println("User Age: ");
		user.setAge(s.nextInt());
		System.out.println("User Salary: ");
		user.setSalary(s.nextInt());
		DAO.saveUser(user);
	}
	
	private static void updateUser() throws SQLException {
		User user = new User();
		System.out.println("User Id: ");
		user.setId(s.nextInt());
		System.out.println("User Name: ");
		user.setName(s.next());
		DAO.updateUser(user);
	}
	
	private static void deleteUser() throws SQLException {
		User user = new User();
		System.out.println("User Id: ");
		user.setId(s.nextInt());
		
		DAO.deleteUser(user);

	}
	
	private static void getAllUsers() throws SQLException {
		DAO.getAllUsers();
	}
	
	private static void getUserById() throws SQLException {
		User user = new User();
		System.out.println("User Id: ");
		user.setId(s.nextInt());
		
		DAO.getUserById(user);
	}
	
	private static void exit() {
		System.exit(0);
	}

}
