
@DB
Feature: Database Testing

  Background: 
    Given I am connected to the database

  Scenario: Getting top three customers with highest credit limit
    When I get the top three customers by credit limit
    Then I print their names and credit limits
    And I close the database connection 
