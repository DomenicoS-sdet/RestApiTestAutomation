package tests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import com.google.gson.Gson;
import utils.SmsPayload;

public class SmsTests extends Tests{
	
	@Before @Override
	public void setup() {
		RestAssured.baseURI = "https://rest.nexmo.com";
	}
	
	
	@Test
	public void sendSmsToNumber() {
		SmsPayload smspayload = new SmsPayload();
		smspayload.api_key = API_KEY;
		smspayload.api_secret = API_SECRET;
		smspayload.to = PHONE_NUMBER;
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
