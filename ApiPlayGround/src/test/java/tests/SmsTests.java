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

public class SmsTests extends Tests{
	
	
	@Test
	public void sendSmsToNumber() {
		SmsPayload smspayload = new SmsPayload();
		smspayload.api_key = API_KEY;
		smspayload.api_secret = API_SECRET;
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
