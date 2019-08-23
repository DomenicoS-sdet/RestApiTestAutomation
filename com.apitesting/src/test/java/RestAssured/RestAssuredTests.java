package RestAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;


public class RestAssuredTests {
	
	@Before
	public void setup() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}
	
	//This test is to check if the correct post with id 1 is returned
	@Test
	public void verifythecorrectinstanceisreturned() {
		get("/posts/1").then().assertThat().body("id", equalTo(1)).and().statusCode(200);
	}
	
	//This test is to check if the correct comments are returned given post id = 1
	//possible bug below since also comments with different postId are returned
	@Test
	public void verifythecommentsrelatedtoapostarereturned() {
		get("/posts/1/comments").then().assertThat().body("postId", everyItem(equalTo(1))).and().statusCode(200);
	}
	
	//This test uses a different endpoint to get the comments given post id = 1
	@Test
	public void verifythecorrectcommentisreturned_byid() {
		get("/comments?postId=1").then().assertThat().body("postId", everyItem(equalTo(1))).and().statusCode(200);
	}
	
	//This test checks if the correct post is returned given user id = 1
	@Test
	public void verifythecorrectpostisreturned_byuserid() {
		get("/posts?userId=1").then().assertThat().body("userId", everyItem(equalTo(1))).and().statusCode(200);
	}
	
	
	//This test checks if a new post can be added and then it tries to retrieve it
	@Test
	public void verifyanewpostcanbecreatedanditsavailable() {
		String payload = 
				"{\n" +
				   " \"userId\": 1,\n" +
				   " \"id\": 101,\n" +
				   " \"title\": \"AutomationTestingTests\",\n" +
				   " \"body\": \"This is a post created in automation\" \n" +
				"}"; 
		
		given().contentType(ContentType.JSON)
			.and()
			.body(payload)
			.when()
			.post("/posts")
			.then().statusCode(201);
		
		get("/posts/101").then().assertThat().body("id", equalTo(101)).and().statusCode(200);
	}
	
	//This test checks if a specific post can be updated using PUT and the post id
	@Test
	public void verifyapostcanbeupdated_PUT() {
		String payload = 
				"{\n" +
				   " \"userId\": 1,\n" +
				   " \"id\": 1,\n" +
				   " \"title\": \"AutomationTestingTests\",\n" +
				   " \"body\": \"This is a post created in automation\" \n" +
				"}"; 
		given().contentType(ContentType.JSON)
			.and()
			.body(payload)
			.when()
			.put("/posts/1")
			.then().statusCode(200);
		
		get("/posts/1")
		.then()
		.assertThat()
		.body("title", equalTo("AutomationTestingTests"))
		.and()
		.body("body", equalTo("This is a post created in automation"))
		.and()
		.statusCode(200);
	}
	
	//This test checks if a specific post can be updated using PATCH and the post id
	//possible bug below as the PATCH returns 500 error
	@Test
	public void verifyapostcanbeupdated_PATCH() {
		String payload = 
				"{\n" +
				   " \"title\": \"AutomationTestingTests\",\n" +
				"}"; 
		given().contentType(ContentType.JSON)
		.and()
		.body(payload)
		.when()
		.patch("/posts/1")
		.then().statusCode(200);
		
		get("/posts/1")
		.then()
		.assertThat()
		.body("title", equalTo("AutomationTestingTests"))
		.and()
		.statusCode(200);
	}
	
	//This test checks if a specific post can be deleted 
	@Test
	public void verifyapostcanbedeleted() {
		delete("/posts/1").then().statusCode(200);
		
		get("/posts/1").then().statusCode(404);
		
	}

}
