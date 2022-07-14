Feature: Pulsar module

  Scenario: Should apply pulsar module
    When I apply legacy modules to default project
      | /api/build-tools/maven                  |
      | /api/servers/spring-boot                |
      | /api/servers/spring-boot/brokers/pulsar |
    Then I should have files in "src/main/java/tech/jhipster/chips/technical/infrastructure/config/pulsar"
      | PulsarProperties.java |
