@OneClass
Feature: Get one class API

  Background: 
    Given I create a request
    And I provided the ClassId 282 as the path parameter

  @tag1
  Scenario: Get One Class and Validate the reponse 
    When I make a GET to the Get one class enpoint 
    And I validate that the status code is 200
    And I validate that the Id in the response body is 282
    Then I validate that the class description contains "Telling the truth"
  
