package AutomationFramework.com.apitesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasItems;
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
	
	private static String payload = 
			"{\n" +
			   " \"userId\": 1,\n" +
			   " \"id\": 101,\n" +
			   " \"title\": \"AutomationTestingTests\",\n" +
			   " \"body\": \"This is a post created in automation\" \n" +
			"}";
	
	//This test checks if a new post can be added and then it tries to retrieve it
	@Test
	public void verifyanewpostcanbecreatedanditsavailable() {
		given().contentType(ContentType.JSON)
			.body(payload)
			.post("/posts")
			.then().statusCode(201);
		
		get("/posts/101").then().assertThat().body("id", equalTo(101)).and().statusCode(200);
	}

}
