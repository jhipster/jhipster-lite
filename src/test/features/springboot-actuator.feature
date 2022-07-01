Feature: Spring Boot Actuator modules

  Scenario: Should add Spring Boot Actuator
    When I apply legacy modules to default project
      | /api/build-tools/maven                            |
      | /api/servers/spring-boot                          |
      | /api/servers/spring-boot/technical-tools/actuator |
    Then I should have files in ""
      | pom.xml |
