#Author: nayanagrawal995@gmail.com

@HomePageLink
Feature: Verify homepage Links

  @HomePageLink
  Scenario: verify homepage Links in trivago magazine
    Given user is on trivago homepage to verify homepage links
    When Verify all the links in homepage
    And Close the browser for homepage