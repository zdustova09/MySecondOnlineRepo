package com.neotech.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCDemo2 {

	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";

	//DB URL Format: 
	//jdbc:jdbctype://ipaddress:portnumber/db_name
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/LibraryMgmt";

	
	public static void main(String[] args) throws SQLException {
		// Retrieve all the book category names and store them in ArrayList
		// Print the ArrayList in the console
		
		//create a connection to the database
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		
		//create a statement using the connection object
		Statement st = conn.createStatement();
		
		
		//execute the statement and save the result in a ResultSet object
		ResultSet rs = st.executeQuery("SELECT * FROM bookcategory");
		
		List<String> categoryNames = new ArrayList<>();
		
		while(rs.next())
		{
			categoryNames.add(rs.getObject("BookCategoryName").toString());
		}
		
		System.out.println(categoryNames);
		
		System.out.println("--------");
		
		for (String name : categoryNames)
		{
			System.out.print(name + " ");
		}
		
		//close the connection and other objects
		rs.close();
		st.close();
		conn.close();

	}

}
