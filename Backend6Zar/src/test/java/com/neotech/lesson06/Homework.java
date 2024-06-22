package com.neotech.lesson06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import org.junit.Test;




public class Homework {
	
	/*
	 * Connect to classicmodels database 
	 * Execute a query to get all information of
	 * customer with id 124
	 * Get the resultset metadata and print the number of columns
	 * Get all column names and store them in an arraylist. 
	 * Print the Arraylist
	 */
	
	//To run queries from Eclipse, we need to connect to the DB, 
	// and for that we need the followings:
	// (1) DB URL
	// (2) DB credentials 
	
	// DB url format: jdbc:jdbcType://dbUrl:portNumber/db_name
	// jdbc:mysql://147.182.216.34:3306/classicmodels
	
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/classicmodels";
	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";
	
	
	@Test
	public void getResults() throws SQLException {
		
		// Create a connection to the DB
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		
		// Create a Statement to run your queries
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM customers WHERE customerNumber = 124");
		
		// We need to get the ResulSet MetaData
		 ResultSetMetaData rsMetaData = rs.getMetaData();
		 
		 // Get the column count -> total number of columns
		 int colCount = rsMetaData.getColumnCount();
		 
		 System.out.println("colCount -> " + colCount);
		 
		 // Get all column names and store them in ArrayList
		 List<String> ls = new ArrayList<>();
		 
		 for (int i = 1; i <= colCount; i++)
		 {
			 ls.add(rsMetaData.getColumnName(i));
		 }
		 
		 System.out.println(ls);
		 
		 
		
		 
		 // Extra part 
		 // To retrieve the results, we usually use a while loop
		 // to iterate over the elements (rows)
		 
		 while (rs.next()) 
		 {
			System.out.println(rs.getString("customerName") + "  " + rs.getString("phone"));
			
		}
		 
		 
		// close all created DB objects
		rs.close();
		st.close();
		conn.close();
		
	}
	
	


}
