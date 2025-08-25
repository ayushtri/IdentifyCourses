Feature: Validate Error on 'Ready to Transform' Form under For Enterprise

  Background:
    Given I open the Coursera homepage

  @Smoke @Fieldlevel
  Scenario: Validating Email field with invalid input
    When user navigates to For Enterprise section
    And user scrolls and clicks on the Campus product
    Then user fills the form with invalid email from Excel sheet

  @Fieldlevel
  Scenario: Validating First Name field when left empty
    When user navigates to For Enterprise section
    And user scrolls and clicks on the Campus product
    Then user leaves First Name field empty and submits the form
    And user retrieves the error message for First Name field

  @Fieldlevel
  Scenario: Validating Last Name field when left empty
    When user navigates to For Enterprise section
    And user scrolls and clicks on the Campus product
    Then user leaves Last Name field empty and submits the form
    And user retrieves the error message for Last Name field

  @Fieldlevel
  Scenario: Validating Phone Number field with invalid input
    When user navigates to For Enterprise section
    And user scrolls and clicks on the Campus product
    Then user fills the Phone Number field with invalid data
    And user retrieves the error message for Phone Number field

  @Fieldlevel
  Scenario: Validating Institution Type dropdown when not selected
    When user navigates to For Enterprise section
    And user scrolls and clicks on the Campus product
    Then user does not select any Institution Type and submits the form
    And user retrieves the error message for Institution Type dropdown

  @Fieldlevel
  Scenario: Validating Institution Name field when left empty
    When user navigates to For Enterprise section
    And user scrolls and clicks on the Campus product
    Then user leaves Institution Name field empty and submits the form
    And user retrieves the error message for Institution Name field

  @Fieldlevel
  Scenario: Validating Job Role dropdown when not selected
    When user navigates to For Enterprise section
    And user scrolls and clicks on the Campus product
    Then user does not select any Job Role and submits the form
    And user retrieves the error message for Job Role dropdown

  @Fieldlevel
  Scenario: Validating Department dropdown when not selected
    When user navigates to For Enterprise section
    And user scrolls and clicks on the Campus product
    Then user does not select any Department and submits the form
    And user retrieves the error message for Department dropdown

  @Fieldlevel
  Scenario: Validating 'What are your needs' dropdown when not selected
    When user navigates to For Enterprise section
    And user scrolls and clicks on the Campus product
    Then user does not select any option in Needs dropdown and submits the form
    And user retrieves the error message for Needs dropdown

  @Fieldlevel
  Scenario: Validating State dropdown when not selected
    When user navigates to For Enterprise section
    And user scrolls and clicks on the Campus product
    Then user selects Country but does not select any State and submits the form
    And user retrieves the error message for State dropdown
