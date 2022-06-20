package tech.jhipster.lite.generator.server.springboot.technicaltools.actuator.infrastructure.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.generator.module.infrastructure.primary.JHipsterModuleApiDoc;
import tech.jhipster.lite.generator.module.infrastructure.primary.JHipsterModuleResource;
import tech.jhipster.lite.generator.server.springboot.technicaltools.actuator.application.SpringBootActuatorApplicationService;

@Configuration
class SpringBootActuatorModuleConfiguration {

  @Bean
  JHipsterModuleResource springBootActuatorModule(SpringBootActuatorApplicationService actuatorModule) {
    return JHipsterModuleResource
      .builder()
      .legacyUrl("/api/servers/spring-boot/technical-tools/actuator")
      .slug("springboot-actuator")
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().addProjectBaseName().build())
      .apiDoc(new JHipsterModuleApiDoc("Spring Boot - Technical tools", "Add a spring-boot actuator to the project"))
      .factory(actuatorModule::buildSpringBootActuatorModule);
  }
}
