package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2SecurityDomainService;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2SecurityService;

@Configuration
public class OAuth2SecurityBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;

  public OAuth2SecurityBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
  }

  @Bean
  public OAuth2SecurityService oauth2SecurityService() {
    return new OAuth2SecurityDomainService(projectRepository, buildToolService, springBootCommonService);
  }
}
