package tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.application.FlywayApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleApiDoc;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertyDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class FlywayModuleConfiguration {

  @Bean
  JHipsterModuleResource flywayModule(FlywayApplicationService flyway) {
    return JHipsterModuleResource
      .builder()
      .legacyUrl("/api/servers/spring-boot/database-migration-tools/flyway")
      .slug("flyway")
      .propertiesDefinition(properties())
      .apiDoc(new JHipsterModuleApiDoc("Spring Boot - Database Migration", "Add Flyway"))
      .organization(JHipsterModuleOrganization.builder().feature("database-migration").addFeatureDependency("jpa-persistence").build())
      .tags("server", "spring", "spring-boot", "database", "migration")
      .factory(flyway::buildModule);
  }

  private JHipsterModulePropertiesDefinition properties() {
    return JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().add(addFlywayMysqlProperty()).build();
  }

  private JHipsterModulePropertyDefinition addFlywayMysqlProperty() {
    return JHipsterModulePropertyDefinition
      .mandatoryBooleanProperty("addFlywayMysql")
      .description("Add Flyway MySQL (or maria) dependency")
      .build();
  }
}
