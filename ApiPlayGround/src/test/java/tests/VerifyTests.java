package tests;

import io.restassured.http.ContentType;
import utils.VerifyPayLoad;
import static io.restassured.RestAssured.given;

import org.junit.Test;

public class VerifyTests extends Tests{
	
	@Test
	public void verifyapinisreturnedanditsvalid() {
		
		VerifyPayLoad payload = new VerifyPayLoad();
		
		payload.api_key = API_KEY;
		payload.api_secret = API_SECRET;
		payload.brand = "NexmoVerifyTest";
		payload.number = PHONE_NUMBER;
		
		 given()
			.contentType(ContentType.JSON)
			.body(payload)
			.when()
			.post("/verify/json")
			.then()
			.statusCode(200);	
		
	}

}
