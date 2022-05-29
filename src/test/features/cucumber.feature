Feature: Cucumber module

  Scenario: Should add cucumber elements
    When I add cucumber to default project with maven file
    Then I should have files in "src/test/java/tech/jhipster/chips/cucumber"
      | CucumberConfiguration.java |