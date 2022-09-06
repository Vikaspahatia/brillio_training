package com.testJDBC2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO{
	static Connection conn = null;
	
	static void saveUser(User user) throws SQLException {
		conn = Utility.createConnection();
		String sql = "insert into user values(?, ?, ?, ?, ?)";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, user.getId());
		statement.setString(2, user.getName());
		statement.setDate(3, user.getDob());
		statement.setInt(4, user.getAge());
		statement.setInt(5, user.getSalary());
		int count = statement.executeUpdate();
		
		System.out.println("User added " + count);
	}
	
	static void getAllUsers() throws SQLException {
		conn = Utility.createConnection();
		Statement statement = conn.createStatement();
		String sql = "select * from user";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {
			System.out.println("User ID: " + rs.getString("id") + " User Name: " + rs.getString("name") + " User DOB: " + rs.getDate("dob") + " User Age: " + rs.getInt("age") + " User Salary: " + rs.getInt("salary"));
		}
	}
	
	static void getUserById(User user) throws SQLException {
		conn = Utility.createConnection();
		String sql = "select * from user where id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, user.getId());
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
			System.out.println("User ID: " + rs.getString("id") + " User Name: " + rs.getString("name") + " User DOB: " + rs.getDate("dob") + " User Age: " + rs.getInt("age") + " User Salary: " + rs.getInt("salary"));
		}
	}
	
	static void updateUser(User user) throws SQLException {
		conn = Utility.createConnection();
		String sql = "update user set name = ? where id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setString(1, user.getName());
		statement.setInt(2, user.getId());
		
		statement.executeUpdate();
	}
	
	static void deleteUser(User user) throws SQLException {
		conn = Utility.createConnection();
		String sql = "delete from user where id = ?";
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, user.getId());
		statement.executeUpdate();
	}
}
