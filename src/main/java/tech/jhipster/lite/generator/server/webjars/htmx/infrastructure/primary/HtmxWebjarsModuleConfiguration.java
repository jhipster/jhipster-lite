package tech.jhipster.lite.generator.server.webjars.htmx.infrastructure.primary;

import static tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.webjars.htmx.application.HtmxWebjarsApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class HtmxWebjarsModuleConfiguration {

  @Bean
  public JHipsterModuleResource htmxWebjarsModule(HtmxWebjarsApplicationService htmxWebjarsModule) {
    return JHipsterModuleResource
      .builder()
      .slug(HTMX_WEBJARS)
      .propertiesDefinition(
        JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addConfigurationFormat().build()
      )
      .apiDoc("WebJars", "Add HTMX webjar to the project")
      .organization(JHipsterModuleOrganization.builder().addDependency(WEBJARS_LOCATOR).build())
      .tags("server", "web", "webjars", "htmx")
      .factory(htmxWebjarsModule::buildWebjarsModule);
  }
}
