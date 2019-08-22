package AutomationFramework.com.apitesting;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;
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
	

}
