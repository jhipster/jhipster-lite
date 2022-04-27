package tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.domain.MongockDomainService;
import tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.domain.MongockService;

@Configuration
public class MongockBeanConfiguration {

  private final BuildToolService buildToolService;
  private final ProjectRepository projectRepository;
  private final SpringBootCommonService springBootCommonService;

  public MongockBeanConfiguration(
    BuildToolService buildToolService,
    ProjectRepository projectRepository,
    SpringBootCommonService springBootCommonService
  ) {
    this.buildToolService = buildToolService;
    this.projectRepository = projectRepository;
    this.springBootCommonService = springBootCommonService;
  }

  @Bean
  public MongockService mongockService() {
    return new MongockDomainService(buildToolService, projectRepository, springBootCommonService);
  }
}
