Feature: Cucumber authentication

  Scenario: Should apply cucumber oauth2 authentication module
    When I apply legacy modules to default project
      | /api/build-tools/maven                                                  |
      | /api/servers/spring-boot/component-tests/cucumber                       |
      | /api/servers/spring-boot/component-tests/cucumber-oauth2-authentication |
    Then I should have files in "src/test/java/tech/jhipster/chips/authentication/infrastructure/primary"
      | AuthenticationSteps.java |
