package tech.jhipster.lite.generator.server.javatool.frontendmaven.infrastructure.primary;

import static tech.jhipster.lite.generator.slug.domain.JHLiteFeatureSlug.*;
import static tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.javatool.frontendmaven.application.FrontendServerApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class FrontendServerModuleConfiguration {

  @Bean
  JHipsterModuleResource frontendMavenModule(FrontendServerApplicationService frontendServer) {
    return JHipsterModuleResource.builder()
      .slug(FRONTEND_MAVEN_PLUGIN)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addIndentation().build())
      .apiDoc("Frontend Server Maven Plugin", "Add Frontend Server Maven Plugin")
      .organization(JHipsterModuleOrganization.builder().addDependency(SPRING_SERVER).addDependency(CLIENT_CORE).build())
      .tags("server", "tools")
      .factory(frontendServer::buildFrontendMavenModule);
  }

  @Bean
  JHipsterModuleResource frontendGradleModule(FrontendServerApplicationService frontendServer) {
    return JHipsterModuleResource.builder()
      .slug(FRONTEND_GRADLE_PLUGIN)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().addIndentation().build())
      .apiDoc("Frontend Server Gradle Plugin", "Add Frontend Server Gradle Plugin")
      .organization(JHipsterModuleOrganization.builder().addDependency(SPRING_SERVER).addDependency(CLIENT_CORE).build())
      .tags("server", "tools")
      .factory(frontendServer::buildFrontendGradleModule);
  }
}
