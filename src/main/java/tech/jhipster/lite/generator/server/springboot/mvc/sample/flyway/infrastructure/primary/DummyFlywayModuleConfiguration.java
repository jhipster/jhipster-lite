package tech.jhipster.lite.generator.server.springboot.mvc.sample.flyway.infrastructure.primary;

import static tech.jhipster.lite.generator.slug.domain.JHLiteFeatureSlug.SAMPLE_SCHEMA;
import static tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.mvc.sample.flyway.application.DummyFlywayApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class DummyFlywayModuleConfiguration {

  @Bean
  JHipsterModuleResource dummyFlywayPostgresqlModule(DummyFlywayApplicationService dummyFlyway) {
    return JHipsterModuleResource.builder()
      .slug(SAMPLE_POSTGRESQL_FLYWAY_CHANGELOG)
      .withoutProperties()
      .apiDoc("Spring Boot - MVC", "Add postgresql flyway changelog for dummy feature")
      .organization(
        JHipsterModuleOrganization.builder()
          .feature(SAMPLE_SCHEMA)
          .addDependency(FLYWAY)
          .addDependency(SAMPLE_FEATURE)
          .addDependency(POSTGRESQL)
          .build()
      )
      .tags("server")
      .factory(dummyFlyway::buildPostgresqlModule);
  }

  @Bean
  JHipsterModuleResource dummyFlywayNotPostgresqlModule(DummyFlywayApplicationService dummyFlyway) {
    return JHipsterModuleResource.builder()
      .slug(SAMPLE_NOT_POSTGRESQL_FLYWAY_CHANGELOG)
      .withoutProperties()
      .apiDoc("Spring Boot - MVC", "Add not postgresql flyway changelog for dummy feature")
      .organization(JHipsterModuleOrganization.builder().feature(SAMPLE_SCHEMA).addDependency(FLYWAY).addDependency(SAMPLE_FEATURE).build())
      .tags("server")
      .factory(dummyFlyway::buildNotPostgresqlModule);
  }
}
