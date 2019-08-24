package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import utils.From;
import utils.To;
import utils.VoicePayLoad;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class VoiceTests extends Tests{
	
	@Before @Override
	public void setup() {
		RestAssured.baseURI = "https://api.nexmo.com";
	}
	
	@Test
	public void testvoicecall() {
		VoicePayLoad payload = new VoicePayLoad();
		payload.to = new ArrayList<To>();
		payload.answer_url = new ArrayList<String>();
		To to = new To();
		From from = new From();
		
		//configure to
		to.type = "phone";
		to.number = 447450816950L;
		
		//configure from
		from.type = "phone";
		from.number = 12345678901L;
		
		//configure final payload
		payload.answer_url.add("https://nexmo-community.github.io/ncco-examples/first_call_talk.json");
		payload.from = from;
		payload.to.add(to);
		
		given().contentType(ContentType.JSON)
			.and()
			.header(new Header("Authorization", "Bearer " + APP_JWT))
			.and()
			.body(payload)
			.when()
			.post("/v1/calls")
			.then()
			.statusCode(201);
	}

}
