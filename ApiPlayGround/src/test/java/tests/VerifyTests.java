package tests;

import io.restassured.http.ContentType;
import utils.VerifyPayLoad;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class VerifyTests extends Tests{
	
	@Test
	public void verifyapinisreturnedanditsvalid() {
		
		VerifyPayLoad payload = new VerifyPayLoad();
		
		payload.api_key = API_KEY;
		payload.api_secret = API_SECRET;
		payload.brand = "NexmoVerifyTest";
		payload.number = PHONE_NUMBER;
		
		String request_id = given()
			.contentType(ContentType.JSON)
			.body(payload)
			.when()
			.post("/verify/json")
			.then()
			.statusCode(200)
			.extract()
			.path("request_id");
		
		assertNotNull(request_id);
		
	}

}
