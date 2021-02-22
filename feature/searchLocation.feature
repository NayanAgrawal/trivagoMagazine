#Author: nayanagrawal995@gmail.com

@SearchLocation
Feature: Search location

  @SearchLocation
  Scenario: Search location in trivago magazine
    Given user is on trivago homepage for search location
    When search for specific location
    Then verify searched location
    And Close the browser for search location