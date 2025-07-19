package tech.jhipster.lite.generator.typescript.sonar.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SONARQUBE_TYPESCRIPT;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.TYPESCRIPT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.typescript.sonar.application.SonarTypescriptApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class SonarTypescriptModuleConfiguration {

  @Bean
  JHipsterModuleResource sonarTypescriptModule(SonarTypescriptApplicationService sonarTypescript) {
    return JHipsterModuleResource.builder()
      .slug(SONARQUBE_TYPESCRIPT)
      .withoutProperties()
      .apiDoc("Typescript", "Add Sonar to project")
      .organization(JHipsterModuleOrganization.builder().addDependency(TYPESCRIPT).build())
      .tags("typescript")
      .factory(sonarTypescript::buildModule);
  }
}
