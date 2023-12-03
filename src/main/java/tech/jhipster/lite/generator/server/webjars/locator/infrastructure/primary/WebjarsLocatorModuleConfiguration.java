package tech.jhipster.lite.generator.server.webjars.locator.infrastructure.primary;

import static tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.webjars.locator.application.WebjarsLocatorApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class WebjarsLocatorModuleConfiguration {

  @Bean
  public JHipsterModuleResource webjarsLocatorModule(WebjarsLocatorApplicationService webjarsModule) {
    return JHipsterModuleResource
      .builder()
      .slug(WEBJARS_LOCATOR)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addConfigurationFormat().build()
      )
      .apiDoc("WebJars", "Add WebJars to the project")
      .organization(JHipsterModuleOrganization.builder().addDependency(SPRING_BOOT_THYMELEAF).build())
      .tags("server", "web")
      .factory(webjarsModule::buildWebjarsModule);
  }
}
