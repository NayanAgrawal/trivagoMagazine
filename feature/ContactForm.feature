#Author: nayanagrawal995@gmail.com

@ContactForm
Feature: Fill contact form

  @ContactForm
  Scenario: Fill contact form in trivago magazine
    Given user is on trivago homepage for contact form
    When Fill contact form
    Then verify sent message
    And Close the browser for contact form