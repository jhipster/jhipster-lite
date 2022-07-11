Feature: Apache Kafka

  Scenario: Should apply Apache Kafka init module
    When I apply legacy modules to default project
      | /api/build-tools/maven                 |
      | /api/servers/spring-boot               |
      | /api/servers/spring-boot/brokers/kafka |
    Then I should have files in "src/main/docker"
      | kafka.yml |

  Scenario: Should apply Apache Kafka dummy producer consumer module
    When I apply "springboot-kafka-dummy-producer-consumer" module to default project with maven file
      | packageName | tech.jhipster.chips |
      | baseName    | jhipster            |
    Then I should have files in "src/main/java/dummy/infrastructure/secondary/kafka/producer"
      | DummyProducer.java |

  Scenario: Should apply Apache Kafka AKHQ module
    When I apply "springboot-kafka-akhq" module to default project with maven file
      | packageName | tech.jhipster.chips |
      | baseName    | jhipster            |
    Then I should have files in "src/main/docker"
      | akhq.yml |
