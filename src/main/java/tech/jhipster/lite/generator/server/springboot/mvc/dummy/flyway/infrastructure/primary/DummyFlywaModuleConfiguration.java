package tech.jhipster.lite.generator.server.springboot.mvc.dummy.flyway.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.dummy.flyway.application.DummyFlywayApplicationService;
import tech.jhipster.lite.module.domain.properties.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.properties.JHipsterModulePropertyDefinition;
import tech.jhipster.lite.module.infrastructure.primary.JHipsterModuleApiDoc;
import tech.jhipster.lite.module.infrastructure.primary.JHipsterModuleResource;

@Configuration
class DummyFlywaModuleConfiguration {

  @Bean
  JHipsterModuleResource dummyFlywayModule(DummyFlywayApplicationService dummyFlyway) {
    return JHipsterModuleResource
      .builder()
      .legacyUrl("/api/servers/spring-boot/features/dummy-flyway-changelog")
      .slug("dummy-flyway-changelog")
      .propertiesDefinition(properties())
      .apiDoc(new JHipsterModuleApiDoc("Spring Boot - MVC", "Add flyway changelog for dummy feature"))
      .tags("server")
      .factory(dummyFlyway::buildModule);
  }

  private JHipsterModulePropertiesDefinition properties() {
    return JHipsterModulePropertiesDefinition.builder().add(usePostGreSQLTypesProperty()).build();
  }

  private JHipsterModulePropertyDefinition usePostGreSQLTypesProperty() {
    return JHipsterModulePropertyDefinition
      .mandatoryBooleanProperty("usePostgreSQLTypes")
      .description("Use PostGreSQL specific types")
      .order(200)
      .build();
  }
}
