package tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import com.google.gson.Gson;
import utils.SmsPayload;

import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.delete;

public class SmsTests {
	
	@Before
	public void setup() {
		RestAssured.baseURI = "https://rest.nexmo.com";
	}
	
	@Test
	public void sendSmsToNumber() {
		SmsPayload smspayload = new SmsPayload();
		smspayload.api_key = "7722cc06";
		smspayload.api_secret = "1DoZ3PzedVj2asOG";
		smspayload.to = "447450816950";
		smspayload.from = "NEXMO";
		smspayload.text = "Hello from Nexmo";
		
		Gson gson = new Gson();
		
		String payload = gson.toJson(smspayload);
		
		given().contentType(ContentType.JSON)
			.and()
			.body(payload)
			.when()
			.post("/sms/json")
			.then().statusCode(200);
	}

}
