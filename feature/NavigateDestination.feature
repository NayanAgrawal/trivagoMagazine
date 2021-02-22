#Author: nayanagrawal995@gmail.com

@NavigateDestination
Feature: Navigate to destination

  @NavigateDestination
  Scenario: Navigate to destination in trivago magazine
    Given user is on trivago homepage to access any destination
    When search for specific destination
    Then verify searched destination
    And Close the browser for Navigate to destination