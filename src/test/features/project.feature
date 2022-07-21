Feature: project management

  Scenario: Should download project
    Given I apply "init" module to default project
      | projectName  | Test project |
    When I download the created project
    Then I should have "test-project.zip" project