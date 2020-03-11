package com.api.tests;

import io.restassured.http.Header;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class OAUTH2GrantTypeTestCase {

	private static final Log log = LogFactory.getLog(OAUTH2GrantTypeTestCase.class);

	@Test
	public void testOAuthWithAuthorizationCode() throws JSONException {

		String client_id = "BBF4A822-2985-4B57-A78D-08925596E7F1";
		String client_secret = "DB0410D1-E3C0-4C47-AEF6-D964420508DD";
		String AUTHORIZATION_CODE = "eyJhbGciOiJSUzI1NiIsImtpZCI6IndWeTZLSE9UTnZoZ2xzSkVIb1c1UzJmaVZFOCIsInR5cCI6IkpXVCIsIng1dCI6IndWeTZLSE9UTnZoZ2xzSkVIb1c1UzJmaVZFOCJ9.eyJuYmYiOjE1NzI5Nzk4MDUsImV4cCI6MTU3Mjk4MzQwNSwiaXNzIjoiaHR0cHM6Ly90ZXN0aWRlbnRpdHkuYWdlbnRjdWJlZC5jb20iLCJhdWQiOlsiaHR0cHM6Ly90ZXN0aWRlbnRpdHkuYWdlbnRjdWJlZC5jb20vcmVzb3VyY2VzIiwiQ3JtU2VydmljZSJdLCJjbGllbnRfaWQiOiJCQkY0QTgyMi0yOTg1LTRCNTctQTc4RC0wODkyNTU5NkU3RjEiLCJzY29wZSI6WyJjcm1TZXJ2aWNlLmZ1bGxfYWNjZXNzIl19.lds3mIGaLNLsYFxbV3EyALf5_05ir7r95dvPK2SmZmvQRGgMnYx0SJv482klo-8Uy-TkCjf1s68s-Ntnj2m3BXKIYKuWx1NzGOMqB1rOeoBPeX_pxJpZ0xoHnUg9duFdAgbMuc0bJ6U89tXbXbBkLZ6tAXzf_GqQmmGMiiitfbq0AQVn4qslot-I2m2Nswdo1myK9N730dyS8lojG3L2GirAUBEgftxvEEWNBLf5Q2vuVhQBythhMJNQc_GoUVeVBw2MkUIEWJYZgZG4uGaD3AKzW3k30AlVNSMuQZZcAEKKufE7FXuL9vo2ipvAC61ZKTyHzqui1rsYe1dSi9Tx5A";
		String BASE_URI = "https://testidentity.agentcubed.com/connect/token";

/*		ExtractableResponse<Response> response = given().contentType("application/x-www-form-urlencoded")
				.formParam("grant_type", "client_credentials").formParam("client_id", client_id)
				.formParam("client_secret", client_secret).formParam("scope", "crmService.full_access").when()
				.post(BASE_URI).then().extract();
		System.out.println("Response= " + response.statusCode() + " statusLine= " + response.statusLine());
		System.out.println(response.body().asString());

		JSONObject jsonObject = new JSONObject(response.body().asString());

		String accessToken = jsonObject.getString("access_token");

		Object tokenType = jsonObject.get("token_type");
		log.info("Oauth Token with type " + tokenType + "   " + accessToken);*/

		
		// 	Test Case: GET Search
		ExtractableResponse<Response> response1 = given()
				.header(new Header("authorization", "Bearer " + AUTHORIZATION_CODE))
				.contentType("application/JSON")
				.get("https://test-crmservice.agentcubed.com/api/v1.0/Customers/search").then().extract();
		System.out.println("Response= " + response1.statusCode() + " statusLine= " + response1.statusLine());
		System.out.println(response1.body().asString());
		
		
		// Test Case: GET Customer ID	
		ExtractableResponse<Response> response2 = given()
				.header(new Header("authorization", "Bearer " + AUTHORIZATION_CODE))
				.contentType("application/JSON")
				.get("https://test-crmservice.agentcubed.com/api/v1.0/Customers/1?groupId=1").then().extract();
		System.out.println("Response= " + response2.statusCode() + " statusLine= " + response2.statusLine());
		System.out.println(response2.body().asString());
		
		
		// Test Case: GET Duplicates
		ExtractableResponse<Response> response3 = given()
				.header(new Header("authorization", "Bearer " + AUTHORIZATION_CODE))
				.contentType("application/JSON")
				.get("https://test-crmservice.agentcubed.com/api/v1.0/Customers/duplicates?groupId=1&page=1").then().extract();
		System.out.println("Response= " + response3.statusCode() + " statusLine= " + response3.statusLine());
		System.out.println(response3.body().asString());		
		
		
		// Test Case: PUT Duplicates by ID	
		ExtractableResponse<Response> response4 = given()
				.header(new Header("authorization", "Bearer " + AUTHORIZATION_CODE))
				.contentType("application/JSON")
				.put("https://test-crmservice.agentcubed.com/api/v1.0/Customers/duplicates/1?groupId=1").then().extract();
		System.out.println("Response= " + response4.statusCode() + " statusLine= " + response4.statusLine());
		System.out.println(response4.body().asString());
		
		
		// Test Case: POST Update Do Not Call Registry by Customer ID			
		ExtractableResponse<Response> response5 = given()
				.header(new Header("authorization", "Bearer " + AUTHORIZATION_CODE))
				.contentType("application/JSON")
				.post("https://test-crmservice.agentcubed.com/api/v1.0/Customers/UpdateCustomerDoNotCallRegistry/1/1").then().extract();
		System.out.println("Response= " + response5.statusCode() + " statusLine= " + response5.statusLine());
		System.out.println(response5.body().asString());
		
	}

}
