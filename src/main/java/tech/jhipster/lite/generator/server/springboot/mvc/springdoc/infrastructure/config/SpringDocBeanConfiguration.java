package tech.jhipster.lite.generator.server.springboot.mvc.springdoc.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain.SpringDocDomainService;
import tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain.SpringDocService;

@Configuration
public class SpringDocBeanConfiguration {

  private final BuildToolService buildToolService;
  private final ProjectRepository projectRepository;
  private final SpringBootCommonService springBootCommonService;

  public SpringDocBeanConfiguration(
    BuildToolService buildToolService,
    ProjectRepository projectRepository,
    SpringBootCommonService springBootCommonService
  ) {
    this.buildToolService = buildToolService;
    this.projectRepository = projectRepository;
    this.springBootCommonService = springBootCommonService;
  }

  @Bean
  public SpringDocService springDocService() {
    return new SpringDocDomainService(buildToolService, projectRepository, springBootCommonService);
  }
}
