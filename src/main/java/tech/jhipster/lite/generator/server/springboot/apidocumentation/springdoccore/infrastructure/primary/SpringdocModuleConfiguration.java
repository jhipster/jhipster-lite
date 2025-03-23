package tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoccore.infrastructure.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.SPRINGDOC;
import static tech.jhipster.lite.shared.slug.domain.JHLiteFeatureSlug.SPRING_MVC_SERVER;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SPRINGDOC_MVC_OPENAPI;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SPRINGDOC_WEBFLUX_OPENAPI;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SPRING_BOOT_WEBFLUX_NETTY;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.apidocumentation.springdoccore.application.SpringdocApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class SpringdocModuleConfiguration {

  private static final String API_GROUP = "Spring Boot - API Documentation";
  private static final String SERVER_TAG = "server";
  private static final String SWAGGER_TAG = "server";
  private static final String SPRING_TAG = "spring";
  private static final String SPRING_BOOT_TAG = "spring-boot";
  private static final String DOCUMENTATION_TAG = "documentation";

  @Bean
  JHipsterModuleResource springdocMvcModule(SpringdocApplicationService springdoc) {
    return JHipsterModuleResource.builder()
      .slug(SPRINGDOC_MVC_OPENAPI)
      .propertiesDefinition(buildPropertiesDefinition())
      .apiDoc(API_GROUP, "Add springdoc-openapi for spring MVC")
      .organization(JHipsterModuleOrganization.builder().feature(SPRINGDOC).addDependency(SPRING_MVC_SERVER).build())
      .tags(SERVER_TAG, SPRING_TAG, SPRING_BOOT_TAG, DOCUMENTATION_TAG, SWAGGER_TAG)
      .factory(springdoc::buildSpringdocMvcModule);
  }

  @Bean
  JHipsterModuleResource springdocWebfluxModule(SpringdocApplicationService springdoc) {
    return JHipsterModuleResource.builder()
      .slug(SPRINGDOC_WEBFLUX_OPENAPI)
      .propertiesDefinition(buildPropertiesDefinition())
      .apiDoc(API_GROUP, "Add springdoc-openapi for webflux")
      .organization(JHipsterModuleOrganization.builder().feature(SPRINGDOC).addDependency(SPRING_BOOT_WEBFLUX_NETTY).build())
      .tags(SERVER_TAG, SPRING_TAG, SPRING_BOOT_TAG, DOCUMENTATION_TAG, SWAGGER_TAG)
      .factory(springdoc::buildSpringdocWebfluxModule);
  }

  private JHipsterModulePropertiesDefinition buildPropertiesDefinition() {
    return JHipsterModulePropertiesDefinition.builder()
      .addBasePackage()
      .addProjectBaseName()
      .addIndentation()
      .addSpringConfigurationFormat()
      .build();
  }
}
