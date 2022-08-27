package tech.jhipster.lite.generator.server.springboot.logging.aop.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.logging.aop.application.AopLoggingApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleApiDoc;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class AOPLoggingModuleConfiguration {

  @Bean
  JHipsterModuleResource aopLoggingModule(AopLoggingApplicationService aopLogging) {
    return JHipsterModuleResource
      .builder()
      .slug("aop-logging")
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectName().addIndentation().build())
      .apiDoc(new JHipsterModuleApiDoc("Spring Boot - Logging", "Add AOP logging"))
      .organization(JHipsterModuleOrganization.SPRINGBOOT_DEPENDENCY)
      .tags("server", "spring", "spring-boot", "log")
      .factory(aopLogging::buildModule);
  }
}
