Feature: JWT modules

  Scenario: Should apply jwt authentication module
    When I apply legacy modules to default project
      | /api/build-tools/maven                        |
      | /api/servers/spring-boot                      |
      | /api/servers/spring-boot/security-systems/jwt |
    Then I should have files in "src/main/java/tech/jhipster/chips/authentication/infrastructure/primary"
      | JWTConfigurer.java |

      
  Scenario: Should apply basic jwt auth module
    When I apply legacy modules to default project
      | /api/build-tools/maven                                   |
      | /api/servers/spring-boot                                 |
      | /api/servers/spring-boot/security-systems/jwt            |
      | /api/servers/spring-boot/security-systems/jwt/basic-auth |
    Then I should have files in "src/main/java/tech/jhipster/chips/account/domain"
      | AuthenticationQuery.java |
