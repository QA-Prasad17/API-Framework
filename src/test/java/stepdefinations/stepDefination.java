package stepdefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

import java.io.IOException;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;

public class stepDefination extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild tc = new TestDataBuild();
	String Place_id;
	JsonPath js;
	static String place_id;

	@Given("Add Place Payload {string} {string} {string}")
	public void add_place_payload(String Name, String Language, String Address) throws IOException {

		res = given().spec(requestSpecification()).body(tc.addPlacePayload(Name, Language, Address));
	}

	@When("User calls {string} with a {string} HTTP request")

	public void user_calls_with_a_http_request(String resource, String method) {
		APIResources resourceapi = APIResources.valueOf(resource);
		System.out.println(resourceapi.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceapi.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().post(resourceapi.getResource());

	}

	@Then("the API call is successful with status code {int}")
	public void the_api_call_is_successful_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in the response body is {string}")
	public void in_the_response_body_is(String keyvalue, String Expectedvalue) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(getJsonPath(response, keyvalue), Expectedvalue);
	}
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String Expectedname, String resource) throws IOException {
	  
		// prepare request spec
		place_id= getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_a_http_request(resource,"GET");
		String Actualname= getJsonPath(response, "name");
		assertEquals(Actualname, Expectedname);
		
	}
	
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(tc.deletePlacePayload(place_id));
	
	}




}
