package com.neotech.api.lesson05;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TopMovies {
	
	@Test
	public void compareMovies() {
		RestAssured.baseURI = "http://199.83.14.77:8080";
		
		Response top100Response = RestAssured.
				given().
					queryParam("count", "100").
				when().
					get("/api/v1/topMovies");
	
		// top100Response.prettyPrint();
		
		int size = top100Response.body().jsonPath().getList("").size();
		// System.out.println("size -> " + size);
		
		System.out.println("----------------------------------------------------------------");

		for (int i = 0; i < size; i++) {
			// I will make a get call for every iteration

			String id = top100Response.body().jsonPath().getString("[" + i + "].id");
			// System.out.println(id);
			int rank = top100Response.body().jsonPath().getInt("[" + i + "].rank");
			String title = top100Response.body().jsonPath().getString("[" + i + "].title");
			String releaseYear = top100Response.body().jsonPath().getString("[" + i + "].releaseYear");

			Response innerMovie = RestAssured.
					given().
						pathParam("id", id).
					when().
						get("/api/v1/movie/{id}");
			
			// innerMovie.prettyPrint();
			
			String innerId = innerMovie.body().jsonPath().getString("id");
			int innerRank = innerMovie.body().jsonPath().getInt("rank");
			String innerTitle = innerMovie.body().jsonPath().getString("title");
			String innerReleaseYear = innerMovie.body().jsonPath().getString("releaseYear");

			System.out.println("Comparing the information of the movie with rank -> " + rank);
			
			if (!id.equals(innerId)) {
				System.out.println("Ids are different! " + id + " != " + innerId);
			}
			
			if (rank != innerRank) {
				System.out.println("Ranks are different! " + rank + " != " + innerRank);
			}
			
			if (title == null || !title.equals(innerTitle)) {
				System.out.println("Titles are different! " + title + " != " + innerTitle);
			}
			
			if (!releaseYear.equals(innerReleaseYear)) {
				System.out.println("Release Years are different! " + releaseYear + " != " + innerReleaseYear);
			}
			
		}

	}

}
