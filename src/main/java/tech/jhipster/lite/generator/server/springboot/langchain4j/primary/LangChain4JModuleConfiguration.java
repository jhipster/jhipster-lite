package tech.jhipster.lite.generator.server.springboot.langchain4j.primary;

import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.LANGCHAIN4J;
import static tech.jhipster.lite.shared.slug.domain.JHLiteModuleSlug.SPRING_BOOT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.langchain4j.application.LangChain4JApplicationService;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleOrganization;
import tech.jhipster.lite.module.domain.resource.JHipsterModulePropertiesDefinition;
import tech.jhipster.lite.module.domain.resource.JHipsterModuleResource;

@Configuration
class LangChain4JModuleConfiguration {

  @Bean
  JHipsterModuleResource langChain4JModule(LangChain4JApplicationService langChain4J) {
    return JHipsterModuleResource.builder()
      .slug(LANGCHAIN4J)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addProjectBaseName().addIndentation().build())
      .apiDoc("LangChain4J", "Add LangChain4J Spring dependency")
      .organization(JHipsterModuleOrganization.builder().addDependency(SPRING_BOOT).build())
      .tags("server", "spring", "spring-boot", "langchain4j")
      .factory(langChain4J::buildModule);
  }
}
