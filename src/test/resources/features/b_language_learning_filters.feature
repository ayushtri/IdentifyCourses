Feature: Extract All Languages and Levels in Language Learning Category

  @Smoke
  Scenario: Display all languages and learning levels with their total counts
    Given I open the Coursera homepage
    When I go to Language Learning
    And I extract the list of all available languages with their respective counts
    And I extract all learning levels with their respective counts
    Then I display the languages and levels with their total counts
