package tech.jhipster.lite.generator.server.springboot.cucumber.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.cucumber.application.CucumberApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleApiDoc;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertyDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class CucumberModuleConfiguration {

  @Bean
  JHipsterModuleResource cucumberModule(CucumberApplicationService cucumber) {
    return JHipsterModuleResource
      .builder()
      .legacyUrl("/api/servers/spring-boot/component-tests/cucumber")
      .slug("springboot-cucumber")
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition
          .builder()
          .addBasePackage()
          .addIndentation()
          .addProjectBaseName()
          .add(jpaResetPropertyDefinition())
          .build()
      )
      .apiDoc(new JHipsterModuleApiDoc("Spring Boot - Component Tests", "Add cucumber integration to project"))
      .organization(JHipsterModuleOrganization.builder().addFeatureDependency("spring-server").build())
      .tags("server", "spring", "spring-boot", "test")
      .factory(cucumber::build);
  }

  private JHipsterModulePropertyDefinition jpaResetPropertyDefinition() {
    return JHipsterModulePropertyDefinition
      .optionalBooleanProperty("jpaReset")
      .description("Used to reset data from all JPA repositories")
      .order(500)
      .build();
  }
}
