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

public abstract class Tests {
	
	protected static String API_KEY = "7722cc06";
	protected static String API_SECRET = "1DoZ3PzedVj2asOG";
	protected static String APP_JWT = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1NjY"
			+ "2MzgzOTUsImp0aSI6IjU2MGUxZTgwLWM2NTAtMTFlOS1iNTU0LTg3ZDZlZmQ0ZmU3NyIsImFwcGx"
			+ "pY2F0aW9uX2lkIjoiNTc3ODU3YzItNDkzMi00NzI1LTgzYjctNDc2NjgzNjRhZTJhIn0.P0twBER"
			+ "rrsmHJfgBQ6igmWOmEBTAz2pIsXqy0DcVVQLjG6SRc_HzZxqg7RMMVGVd8Lsm_lINC5FZye857gv"
			+ "GA1Ih_WpENrLESdI1en9Iz2NzYPNSBtWkMscTy-oyEJbd1qZrYuC-sdo2_bCwi7ygdWQtALjPvPS"
			+ "mA0XV9A4rW02xx3irNzzWJRYdr0u0lvw7ATw2zR-S4TRfGdJVrlNHS6cePtPCcufp6a77ccdQAFo"
			+ "QX5XkEEaePKR89qg8Z3T6evsZGfHCjAyEsOmWajpWI4AtsYOxRHzf7qFabYPCZ9bF-WAGh2Fn4xT"
			+ "X-x56ddwuM6GRS65CTAxY80t1g9IjTQ";
	
	@Before
	public void setup() {
		RestAssured.baseURI = "https://rest.nexmo.com";
	}

}
