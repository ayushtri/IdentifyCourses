Feature: Search Web Development Courses for Beginners in English

  @Smoke
  Scenario: Extract first two beginner-level web development courses in English
    Given I open the Coursera homepage
    When I search for courses of "Web Development"
    And I apply the filter for Beginner level
    And I apply the filter for English language
    Then I should see the first 2 courses displayed and display their course names, total learning hours and ratings