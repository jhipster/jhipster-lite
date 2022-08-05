package tech.jhipster.lite.generator.server.springboot.mvc.dummy.mongopersistence.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.dummy.mongopersistence.application.DummyMongoDBPersistenceApplicationService;
import tech.jhipster.lite.module.domain.properties.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.infrastructure.primary.JHipsterModuleApiDoc;
import tech.jhipster.lite.module.infrastructure.primary.JHipsterModuleResource;

@Configuration
class DummyMongoDBPersistenceModuleConfiguration {

  @Bean
  JHipsterModuleResource dummyMongoDBModule(DummyMongoDBPersistenceApplicationService dummyMongoDBPersistence) {
    return JHipsterModuleResource
      .builder()
      .legacyUrl("/api/servers/spring-boot/features/dummy-mongodb-persistence")
      .slug("dummy-mongodb-persistence")
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().build())
      .apiDoc(new JHipsterModuleApiDoc("Spring Boot - MVC", "Add MongoDB persistence for dummy feature"))
      .tags("server")
      .factory(dummyMongoDBPersistence::buildModule);
  }
}
