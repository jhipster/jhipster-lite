package tech.jhipster.lite.generator.server.springboot.broker.kafka.infrastructure.primary.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.broker.kafka.application.KafkaApplicationService;
import tech.jhipster.lite.module.infrastructure.primary.JHipsterModuleApiDoc;
import tech.jhipster.lite.module.infrastructure.primary.JHipsterModuleResource;

@Configuration
public class KafkaResourceConfiguration {

  private static final String TAG = "Spring Boot - Broker";

  @Bean
  JHipsterModuleResource kafkaResourceInit(KafkaApplicationService kafkaApplicationService) {
    return JHipsterModuleResource
      .builder()
      .legacyUrl("/api/servers/spring-boot/brokers/kafka")
      .slug("springboot-kafka")
      .withoutProperties()
      .apiDoc(new JHipsterModuleApiDoc(TAG, "Add Kafka dependencies, with testcontainers"))
      .factory(kafkaApplicationService::init);
  }
}
