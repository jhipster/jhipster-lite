Feature: jQAssistant module

  Scenario: Should apply jQAssistant module
    When I apply "jqassistant" module to default project with maven file
      | packageName | tech.jhipster.chips |
    Then I should have "<artifactId>jqassistant-maven-plugin</artifactId>" in "pom.xml"
    And I should have files in "."
      | .jqassistant.yml |
