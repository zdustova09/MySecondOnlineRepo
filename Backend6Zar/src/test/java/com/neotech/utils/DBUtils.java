package com.neotech.utils;

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

public class DBUtils {

	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;

	// The main reason for this class is to ensure
	// that we don't repeat ourselves in every test/query

	// Therefore, we will get the connected, process data,
	// and terminate the connect using the same class methods

	// Connect to the DB
	public static void getConnection() {

		ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

		try {
			conn = DriverManager.getConnection(ConfigsReader.getProperty("dbUrl"),
					ConfigsReader.getProperty("dbUserName"), ConfigsReader.getProperty("dbPassword"));
		} catch (SQLException e) {
			System.out.println("Could NOT connect to the DB!!");
			e.printStackTrace();
		}
	}

	// Get data
	public static List<Map<String, String>> storeDataFromDB(String sqlQuery) {
		List<Map<String, String>> listOfMaps = new ArrayList<>();

		try {
			st = conn.createStatement();

			rs = st.executeQuery(sqlQuery);

			ResultSetMetaData rsMetaData = rs.getMetaData();
			int colCount = rsMetaData.getColumnCount();

			Map<String, String> map;

			// Iterate over the rows/records
			while (rs.next()) {
				// Initialize the new map
				map = new LinkedHashMap<>();

				// Fill out the map using the metadata in a dynamic way
				for (int i = 1; i <= colCount; i++) {
					map.put(rsMetaData.getColumnName(i), rs.getString(i));
				}

				listOfMaps.add(map);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfMaps;
	}

	// Close the connection
	public static void closeConnection() {
		try {
			if (rs != null) {
				rs.close();
			}
			
			if (st != null) {
				st.close();
			}
			
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
