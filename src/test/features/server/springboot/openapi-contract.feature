Feature: OpenAPI contract generation

  Scenario: Should apply OpenAPI contract module
    When I apply "openapi-contract" module to default project with maven file
      | packageName | tech.jhipster.chips |
    Then I should have "openapi-maven-plugin" in "pom.xml"
