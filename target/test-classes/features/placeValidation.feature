Feature: Validating place API's
@SmokeTest @Regression
Scenario Outline: Verify if place is successfully added using AddPlaceAPI
Given Add Place Payload "<Name>" "<Language>" "<Address>"
When User calls "AddPlaceAPI" with a "POST" HTTP request
Then the API call is successful with status code 200
And "status" in the response body is "OK"
And "scope" in the response body is "APP"
And verify place_id created maps to "<Name>" using "getPlaceAPI"

Examples:
|Name      | Language |Address	    |
|QA prasad | English  | Test Address|
#|QA Rakesh | French   | New Chicago |

@DeletePlace @Regression
Scenario: Verify if delete place functionality is working
Given DeletePlace Payload
When User calls "deletePlaceAPI" with a "POST" HTTP request
Then the API call is successful with status code 200
And "status" in the response body is "OK"

