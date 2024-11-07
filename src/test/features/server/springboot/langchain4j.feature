Feature: LangChain4j module

  Scenario: Should add Spring Boot LangChain4j Starter
    When I apply modules to default project
      | maven-java  |
      | spring-boot |
      | langchain4j |
    Then I should have entries in "src/main/resources/config/application.yml"
      | open-ai     |
      | langchain4j |
