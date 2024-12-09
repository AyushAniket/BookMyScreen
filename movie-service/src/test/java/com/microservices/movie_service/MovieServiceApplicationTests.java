package com.microservices.movie_service;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieServiceApplicationTests {

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@ServiceConnection
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			"postgres"
	);
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}


	@Test
	void shouldRegisterMovie() {
		String movieJson = """
				{
				  "movieName": "The Matrix",
				  "description": "A science fiction action film about a dystopian future where reality is not what it seems.",
				  "duration": 136,
				  "releaseDate": "1999-03-31T00:00:00Z",
				  "isDisplay": true,
				  "movieTrailerUrl": "https://www.example.com/trailer/the-matrix",
				  "categoryId": 1,
				  "directorId": 1
				}
				""";

		Response response = RestAssured.given()
				.contentType("application/json")
				.body(movieJson)
				.when()
				.post("api/movie/movies")
				.then()
				.statusCode(201) // Expecting HTTP 201 Created
				.extract().response();

		// Optionally, assert the movie was created with correct values
		assertNotNull(response.getBody().jsonPath().getString("movieId")); // Assuming the response includes a generated movie ID
		assertEquals("The Matrix", response.getBody().jsonPath().getString("movieName"));
		assertEquals("A science fiction action film about a dystopian future where reality is not what it seems.",
				response.getBody().jsonPath().getString("description"));

	}
}
