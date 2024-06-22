@createClass
Feature: Create and Validate Class Information

  Background:
    Given a valid OAuth2 token is available

  Scenario: Create a new class and retrieve class information
    When I create a new class and store the class id
    Then I get the class using the stored id
    And I validate the information of the retrieved class