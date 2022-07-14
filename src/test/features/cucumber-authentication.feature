Feature: Cucumber authentication

  Scenario: Should apply cucumber authentication module
    When I apply legacy modules to default project
      | /api/build-tools/maven                                           |
      | /api/servers/spring-boot/component-tests/cucumber                |
      | /api/servers/spring-boot/component-tests/cucumber-authentication |
    Then I should have files in "src/test/java/tech/jhipster/chips/authentication/infrastructure/primary"
      | AuthenticationSteps.java |
