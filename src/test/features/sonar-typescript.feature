Feature: Sonar-typescript

  Scenario: Should apply sonar typescript module
    When I apply "sonar-typescript" module to default project with package json without parameters
    Then I should have files in "."
      | sonar-project.properties |
    Then I should have files in "documentation"
      | sonar.md |
