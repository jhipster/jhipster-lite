package tech.jhipster.lite.generator.server.webjars.infrastructure.primary;

import static tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug.SPRING_BOOT_THYMELEAF;
import static tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug.WEBJARS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.webjars.application.WebjarsApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class WebjarsModuleConfiguration {

  @Bean
  public JHipsterModuleResource webjarsModule(WebjarsApplicationService webjarsModule) {
    return JHipsterModuleResource
      .builder()
      .slug(WEBJARS)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addConfigurationFormat().build()
      )
      .apiDoc("WebJars", "Add WebJars to the project")
      .organization(JHipsterModuleOrganization.builder().addDependency(SPRING_BOOT_THYMELEAF).build())
      .tags("server", "web")
      .factory(webjarsModule::buildWebjarsModule);
  }
}
