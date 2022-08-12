Feature: Flyway module

  Scenario: Should apply flyway initialization module
    When I apply "flyway" module to default project with maven file
      | packageName | tech.jhipster.chips |
    Then I should have "<artifactId>flyway-core</artifactId>" in "pom.xml"

  Scenario: Should apply flyway mysql dependency module
    When I apply "flyway-mysql" module to default project with maven file without properties
    Then I should have "<artifactId>flyway-mysql</artifactId>" in "pom.xml"
