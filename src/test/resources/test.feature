Feature: Authorization and User Get Information About User

  Background:
    Given  I have the API BaseUri And RestAssured Configuration

  Scenario: Retrieve user information using the obtained authorization token
    When I send a POST request to "/authorize" with username and password
    Then the response code should be 200
    And the response should have message "success"
    And I save the value of the token field as authorizationToken

    When I send a GET request to "/info" with the authorizationToken header
    Then the response code should be 200
    And the response should return message "SUCCESS"


  Scenario: Retrieve user information using invalid token

    When I send a GET request to "/info" with the invalid "invalid_token" authorizationToken header
    Then the response code should be 401