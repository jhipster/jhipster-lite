package tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.infrastructure.primary;

import static tech.jhipster.lite.generator.slug.domain.JHLiteFeatureSlug.DATABASE_MIGRATION;
import static tech.jhipster.lite.generator.slug.domain.JHLiteFeatureSlug.JPA_PERSISTENCE;
import static tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.dbmigration.flyway.application.FlywayApplicationService;
import tech.jhipster.lite.module.domain.resource.*;

@Configuration
class FlywayModuleConfiguration {

  @Bean
  JHipsterModuleResource flywayInitializationModule(FlywayApplicationService flyway) {
    return JHipsterModuleResource.builder()
      .slug(FLYWAY)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().addSpringConfigurationFormat().build()
      )
      .apiDoc("Spring Boot - Database Migration", "Add Flyway")
      .organization(JHipsterModuleOrganization.builder().feature(DATABASE_MIGRATION).addDependency(JPA_PERSISTENCE).build())
      .tags("server", "spring", "spring-boot", "database", "migration")
      .factory(flyway::buildInitializationModule);
  }

  @Bean
  JHipsterModuleResource flywayMysqlModule(FlywayApplicationService flyway) {
    return JHipsterModuleResource.builder()
      .slug(FLYWAY_MYSQL)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().build())
      .apiDoc("Spring Boot - Database Migration", "Add Flyway MySQL")
      .organization(JHipsterModuleOrganization.builder().addDependency(FLYWAY).addDependency(MYSQL).build())
      .tags("server", "spring", "spring-boot", "database", "migration")
      .factory(flyway::buildMysqlDependencyModule);
  }

  @Bean
  JHipsterModuleResource flywayMariaDBModule(FlywayApplicationService flyway) {
    return JHipsterModuleResource.builder()
      .slug(FLYWAY_MARIADB)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().build())
      .apiDoc("Spring Boot - Database Migration", "Add Flyway MariaDB")
      .organization(JHipsterModuleOrganization.builder().addDependency(FLYWAY).addDependency(MARIADB).build())
      .tags("server", "spring", "spring-boot", "database", "migration")
      .factory(flyway::buildMysqlDependencyModule);
  }

  @Bean
  JHipsterModuleResource flywayPostgresModule(FlywayApplicationService flyway) {
    return JHipsterModuleResource.builder()
      .slug(FLYWAY_POSTGRESQL)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().build())
      .apiDoc("Spring Boot - Database Migration", "Add Flyway PostgreSQL")
      .organization(JHipsterModuleOrganization.builder().addDependency(FLYWAY).addDependency(POSTGRESQL).build())
      .tags("server", "spring", "spring-boot", "database", "migration")
      .factory(flyway::buildPostgresqlDependencyModule);
  }

  @Bean
  JHipsterModuleResource flywayMsSqlServerModule(FlywayApplicationService flyway) {
    return JHipsterModuleResource.builder()
      .slug(FLYWAY_MSSQL)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addIndentation().build())
      .apiDoc("Spring Boot - Database Migration", "Add Flyway PostgreSQL")
      .organization(JHipsterModuleOrganization.builder().addDependency(FLYWAY).addDependency(MSSQL).build())
      .tags("server", "spring", "spring-boot", "database", "migration")
      .factory(flyway::buildMsSqlServerDependencyModule);
  }
}
