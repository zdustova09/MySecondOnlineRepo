package com.neotech.lesson05;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class MetaDataDemo {

	public static String dbUserName = "user1";
	public static String dbPassword = "Neotech@123";

	//DB URL Format: 
	//jdbc:jdbctype://ipaddress:portnumber/db_name
	public static String dbUrl = "jdbc:mysql://147.182.216.34:3306/classicmodels";
	
	
	@Test
	public void dbMetaData() throws SQLException
	{
		//Database MetaData --> information about the database itself
		
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		
		DatabaseMetaData dbMetaData = conn.getMetaData();
		String driverName = dbMetaData.getDriverName();
		System.out.println(driverName);
		
		String dbVersion = dbMetaData.getDatabaseProductVersion();
		System.out.println(dbVersion);
		
		String dbName = dbMetaData.getDatabaseProductName();
		System.out.println(dbName);
		
		conn.close();
	}
	
	@Test
	public void rsMetaData() throws SQLException
	{
		//ResultSet MetaData --> information about the resultSet
		
		Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery("SELECT * FROM employees WHERE employeeNumber < 1111");
		
		
		//now that we have a ResultSet, I can look into its MetaData
		ResultSetMetaData  rsMetaData = rs.getMetaData();
		
		//what can I get from the rsMetaData
		
		//get the total number of columns
		
		int columnCount = rsMetaData.getColumnCount();
		System.out.println("Column Count --> " + columnCount);
		
		
		//get the name of the first column??
		String columnName1 = rsMetaData.getColumnName(1);
		System.out.println("columnName1 --> " + columnName1);
		
		
		//get the name of the third column
		String columnName3 = rsMetaData.getColumnName(3);
		System.out.println("columnName3 --> " + columnName3);
		
		
		System.out.println("Printing all the column types!");
		
		for (int i = 1; i <= columnCount; i++)
		{
			String columnType = rsMetaData.getColumnTypeName(i);
			System.out.println("ColumnType: " + i + " --> " + columnType);
		}
		
		
		rs.close();
		st.close();
		conn.close();
		
	}
	
}
