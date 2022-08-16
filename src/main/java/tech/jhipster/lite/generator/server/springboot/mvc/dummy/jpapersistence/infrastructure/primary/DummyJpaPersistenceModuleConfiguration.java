package tech.jhipster.lite.generator.server.springboot.mvc.dummy.jpapersistence.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.dummy.jpapersistence.application.DummyJpaPersistenceApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleApiDoc;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class DummyJpaPersistenceModuleConfiguration {

  @Bean
  JHipsterModuleResource dummyJpaPersistenceModule(DummyJpaPersistenceApplicationService dummyJpaPersistence) {
    return JHipsterModuleResource
      .builder()
      .legacyUrl("/api/servers/spring-boot/features/dummy-jpa-persistence")
      .slug("dummy-jpa-persistence")
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().build())
      .apiDoc(new JHipsterModuleApiDoc("Spring Boot - MVC", "Add JPA persistence for dummy feature"))
      .organization(
        JHipsterModuleOrganization
          .builder()
          .addFeatureDependency("dummy-schema")
          .addModuleDependency("springboot-cucumber-jpa-reset")
          .build()
      )
      .tags("server")
      .factory(dummyJpaPersistence::buildModule);
  }
}
