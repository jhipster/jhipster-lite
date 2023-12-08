Feature: Spring Boot Thymeleaf module

  Scenario: Should add Spring Boot Thymeleaf Starter
    When I apply modules to default project
      | maven-java            |
      | spring-boot           |
      | spring-boot-thymeleaf |
    Then I should have "<artifactId>spring-boot-starter-thymeleaf</artifactId>" in "pom.xml"
    And I should have "<artifactId>thymeleaf-layout-dialect</artifactId>" in "pom.xml"

  Scenario: Should apply Thymeleaf Template module
    When I apply modules to default project
      | maven-java            |
      | spring-boot           |
      | spring-boot-thymeleaf |
      | thymeleaf-template |
    Then I should have files in "src/main/resources/templates"
      | index.html |
