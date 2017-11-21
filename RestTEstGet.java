package com.myapp.cxf.form;



import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

 
public  class RestTEstGet extends Object{
	
	@Test
	public void GetWeatherDetails()
	{   
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
 
		// Get the RequestSpecification of the request that you want to sent
		// to the server. The server is specified by the BaseURI that we have
		// specified in the above step.
		RequestSpecification httpRequest = RestAssured.given();
 
		// Make a request to the server by specifying the method Type and the method URL.
		// This will return the Response from the server. Store the response in a variable.
		Response response = httpRequest.request(Method.GET, "/Hyderabad");
 
		// Now let us print the body of the message to see what response
		// we have recieved from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
 
	}
 
	@Test
	public void VerifyCityInJsonResponse()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");
	 
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
	 
		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("City");
	 
		// Let us print the city variable to see what we got
		System.out.println("City received from Response " + city);
	 
		// Validate the response
		Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");
	 
	}
	
	
	@Test
	public void testMyApp() {
		
		//RequestSpecification requestSpecification = new RestAssuredConfiguration().getRequestSpecification();
		RestAssured.baseURI ="http://localhost:8080/MyApp/services/crud/myCrudService";
		RequestSpecification req = RestAssured.given();
		//http://localhost:8080/MyApp/services/myAppService/getFibSeries/?id=1
		/*RestAssured.baseURI ="http://localhost:8080/MyApp/services/crud/myCrudService";
		RequestSpecification req = RestAssured.given();*/

		Response r = given().
        parameters("fName", "w", "lName", "Doe","address","ww","city","ww").
when().
        post("/add");
		
		System.out.println(r.getBody().print());

		/*  req.formParams("fName", "s","lName", "Doe","address","ww","city","ww").log().all();
	        given().spec(req).post("/add").
	                then().statusCode(200).log().all();
	*/
	
	
	}
	
	@Test()
	public void testfib() {
		RestAssured.baseURI = "http://localhost:8080/MyApp/services/myAppService";
		RequestSpecification req = RestAssured.given();
		Response r = given().parameters("id",2).when().get("/getFibSeries");
		//Response r = (Response) req.formParam("id", 1).given().spec(req).get("/getFibSeries").then().statusCode(200).log().all();
		System.out.println(r.body().print());
		
		
	}
	
}
