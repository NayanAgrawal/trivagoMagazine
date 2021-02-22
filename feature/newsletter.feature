#Author: nayanagrawal995@gmail.com

@Newsletter
Feature: Subscribe Newsletter

  @Newsletter
  Scenario: Subscribe Newsletter in trivago magazine
    Given user is on trivago homepage to subscribe newsletter 
    When Enter user emailID
    Then verify successful subscription
    And Close the browser for subscribe newsletter