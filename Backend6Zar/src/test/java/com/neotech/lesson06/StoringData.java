package com.neotech.lesson06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.neotech.utils.ConfigsReader;
import com.neotech.utils.Constants;

public class StoringData {

	@Test
	public void getAndStoreData() throws SQLException {
		// Read the connection properties from configuration.properties file
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		Connection conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"),
				ConfigsReader.getProperty("dbUserName"), ConfigsReader.getProperty("dbPassword"));
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT employeeNumber, lastName, firstName, email FROM employees LIMIT 5 ");

		List<Map<String, String>> listOfMaps = new ArrayList<>();

		Map<String, String> map;

		while (rs.next()) {
			// create a map for each row/record, then add it to the list
			map = new LinkedHashMap<>();
			map.put("Employee Number", rs.getString("employeeNumber"));
			map.put("Last Name", rs.getString("lastName"));
			map.put("First Name", rs.getString("firstName"));
			map.put("Email", rs.getString("email"));

			listOfMaps.add(map);

		}

		System.out.println("-------------------------------------------Hard Coded Method--------------------------------------------");
		for (Map<String, String> list : listOfMaps) {
			System.out.println(list);
		}

		rs.close();
		st.close();
		conn.close();
	}

	// Let's imporve the structure a bit further

	@Test
	public void getAndStoreData1() throws SQLException {
		// Read the connection properties from configuration.properties file
		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);
		Connection conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"),
				ConfigsReader.getProperty("dbUserName"), ConfigsReader.getProperty("dbPassword"));
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT employeeNumber, lastName, firstName, email FROM employees LIMIT 5 ");

		ResultSetMetaData rsMetaData = rs.getMetaData();
		int colCount = rsMetaData.getColumnCount();

		List<Map<String, String>> listOfMaps = new ArrayList<>();
		Map<String, String> map;
		// Iterate over the rows/records 
		while (rs.next()) {
			map = new LinkedHashMap<>();
			// Initialize a new loop
			for (int i = 1; i <= colCount; i++) {
				map.put(rsMetaData.getColumnName(i), rs.getString(i));
			}

			listOfMaps.add(map); 
		}
		System.out.println("------------------------------------------With Enhanced Method-----------------------------------------");
		for (Map<String, String> list : listOfMaps) {
			System.out.println(list);
		}
  

		rs.close();
		st.close();
		conn.close();
	}
}
