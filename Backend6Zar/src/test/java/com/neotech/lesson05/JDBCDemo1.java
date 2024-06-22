package com.neotech.lesson05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo1 {

	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";

	//DB URL Format: 
	//jdbc:jdbctype://ipaddress:portnumber/db_name
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/LibraryMgmt";
	
	
	public static void main(String[] args) throws SQLException {
		// we have all the information needed to connect to a database
		// What should I do?
		/*
		 * HostName: 147.182.216.34
		 * Port: 3306
		 * DB Name: LibraryMgmt
		 * UserName:user1
		 * Password: Neotech@123
		 */
		
		//1. We need to create a connection to the database
		
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		
		System.out.println("Database Connection is Successful!!!");
		
		//create a statement 
		Statement st = conn.createStatement();
		
		//execute a query
		ResultSet rs = st.executeQuery("SELECT * FROM book");
		
		System.out.println("----------------");
		
		System.out.println(rs.toString());
		
		//how would I get the values from this resultSet
				
		rs.next(); //go to the next element
		//getting values using column label
		String bookName1 = rs.getString("BookName");
		System.out.println(bookName1);
		
		rs.next();
		//getting values using column index
		String bookName2 = rs.getString(2);
		System.out.println(bookName2);
		
		rs.next();
		String bookName3 = rs.getObject("BookName").toString();
		System.out.println(bookName3);
		
		System.out.println("Getting values using a loop!!!!");
		
		while(rs.next())
		{
			String bookName = rs.getObject("BookName").toString();
			System.out.println(bookName);
		}
		
		//close the connection and other objects
		rs.close();
		st.close();
		conn.close();
	}
}
