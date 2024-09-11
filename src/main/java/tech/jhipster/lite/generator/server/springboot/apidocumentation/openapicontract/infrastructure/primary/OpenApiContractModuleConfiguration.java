package tech.jhipster.lite.generator.server.springboot.apidocumentation.openapicontract.infrastructure.primary;

import static tech.jhipster.lite.generator.slug.domain.JHLiteFeatureSlug.SPRING_MVC_SERVER;
import static tech.jhipster.lite.generator.slug.domain.JHLiteModuleSlug.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.server.springboot.apidocumentation.openapicontract.application.OpenApiContractApplicationService;
import tech.jhipster.lite.module.domain.resource.*;

@Configuration
class OpenApiContractModuleConfiguration {

  @Bean
  JHipsterModuleResource openApiContractModule(OpenApiContractApplicationService openApiContract) {
    return JHipsterModuleResource.builder()
      .slug(OPENAPI_CONTRACT)
      .propertiesDefinition(JHipsterModulePropertiesDefinition.builder().addBasePackage().build())
      .apiDoc("Spring Boot - OpenAPI", "Generates OpenAPI contract at build time using openapi-maven-plugin")
      .organization(JHipsterModuleOrganization.builder().addDependency(SPRING_MVC_SERVER).addDependency(MAVEN_JAVA).build())
      .tags("server", "spring", "spring-boot", "documentation", "swagger", "openapi")
      .factory(openApiContract::buildModule);
  }
}
