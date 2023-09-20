package StepDefinitions;

import Data.Constants;
import Data.RequestModels.UserCredentials;
import Data.ResponseModels.AuthorizationResponse;
import Data.ResponseModels.InfoResponse;
import Utils.RandomUserGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

public class AuthorizationAndGetInfoStep {
    private static Response response;
    private AuthorizationResponse authorizationResponse;
    private InfoResponse infoResponse;
    private String authorizationToken;
    @Given("I have the API BaseUri And RestAssured Configuration")
    public void iHaveTheAPIBaseUriAndRestAssuredConfiguration() {
        RestAssured.baseURI = Constants.BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();
    }

    @When("I send a POST request to {string} with username and password")
    public void iSendAPOSTRequestToWithTheFollowingJSONRequestBody(String endpoint) {
        String username = RandomUserGenerator.generateRandomUsername();
        String password = RandomUserGenerator.generateRandomPassword();
        UserCredentials userCredentials = new UserCredentials(username,password);
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(userCredentials)
                .when()
                .post(endpoint).then().log().all().extract().response();
        authorizationResponse = response.as(AuthorizationResponse.class);
    }

    @Then("the response code should be {int}")
    public void theResponseCodeShouldBe(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    @And("the response should have message {string}")
    public void theResponseShouldHaveMessage(String message) {
        Assert.assertEquals(authorizationResponse.getMessage(),message);
    }

    @And("I save the value of the token field as authorizationToken")
    public void iSaveTheValueOfTheTokenFieldAs() {
        authorizationToken = authorizationResponse.getToken();
    }

    @When("I send a GET request to {string} with the authorizationToken header")
    public void iSendAGETRequestToWithTheFollowingHeaders(String endpoint) {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",authorizationToken)
                .when()
                .get(endpoint).then().log().all().extract().response();
        infoResponse = response.as(InfoResponse.class);
    }

    @And("the response should return message {string}")
    public void theResponseShouldReturnMessage(String message) {
        Assert.assertEquals(infoResponse.getMessage(),message);
    }

    @When("I send a GET request to {string} with the invalid {string} authorizationToken header")
    public void iSendAGETRequestToWithTheInvalidAuthorizationTokenHeader(String endpoint, String invalidToken) {
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",invalidToken)
                .when()
                .get(endpoint).then().log().all().extract().response();
        infoResponse = response.as(InfoResponse.class);
    }
}
