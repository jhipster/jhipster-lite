Feature: WebJars Locator module

  Scenario: Should add WebJars locator
    When I apply modules to default project
      | maven-java            |
      | spring-boot           |
      | spring-boot-thymeleaf |
      | webjars-locator       |
    Then I should have "<artifactId>webjars-locator</artifactId>" in "pom.xml"
