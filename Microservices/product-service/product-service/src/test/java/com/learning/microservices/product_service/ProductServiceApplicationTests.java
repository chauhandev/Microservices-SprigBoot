package com.learning.microservices.product_service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
    
	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");
	
	@LocalServerPort
	private Integer port;
	
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}
	
	static {
		mongoDBContainer.start();
	}
	@Test
	void shouldCreateProduct() {
		String request = "{\n"
				+ "  \"name\":\"iphone26pro\",\n"
				+ "  \"description\":\"Advanced iphone pro\",\n"
				+ "  \"price\":\"1500000\"\n"
				+ "}";
		 RestAssured.given().contentType("application/json")
		.body(request).when().post("api/product").then().statusCode(201)
		.body("id",Matchers.equalTo("iphone26pro"));
	}

}
