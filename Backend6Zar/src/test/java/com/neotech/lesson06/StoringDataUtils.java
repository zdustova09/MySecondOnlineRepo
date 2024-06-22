package com.neotech.lesson06;


import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.neotech.utils.DBUtils;


public class StoringDataUtils extends DBUtils {

	@Test
	public void getDataFromDB() {
		// Create the connection using DBUtils
		getConnection();

		String query = "SELECT orderDate AS Date, 'Order'"
				+ " AS DateType FROM orders UNION ALL "
				+ "SELECT paymentDate AS Date, 'Payment' "
				+ "AS DateType FROM payments ;";
		// Execute Query and get it in a list of maps
		List<Map<String, String>> lm = storeDataFromDB(query);

		// Processing the data
		System.out.println(lm);

		// Close the connection
		closeConnection();
	}

}
