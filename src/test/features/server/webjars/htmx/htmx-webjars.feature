Feature: HTMX webjars module

  Scenario: Should add HTMX webjars
    When I apply modules to default project
      | maven-java            |
      | spring-boot           |
      | spring-boot-thymeleaf |
      | webjars-locator       |
      | htmx-webjars          |
    Then I should have "<artifactId>htmx.org</artifactId>" in "pom.xml"
