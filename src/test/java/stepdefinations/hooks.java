package stepdefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		
		
		stepDefination sd = new stepDefination();
		if(sd.Place_id==null)
		{
		sd.add_place_payload("prasad", "English", "Asia");
		sd.user_calls_with_a_http_request("AddPlaceAPI", "POST");
		sd.verify_place_id_created_maps_to_using("prasad", "getPlaceAPI");
		}
	}
}
