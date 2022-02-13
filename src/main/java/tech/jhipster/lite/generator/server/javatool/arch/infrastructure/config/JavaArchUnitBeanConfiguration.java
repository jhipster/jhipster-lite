package tech.jhipster.lite.generator.server.javatool.arch.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.javatool.arch.domain.JavaArchUnitDomainService;
import tech.jhipster.lite.generator.server.javatool.arch.domain.JavaArchUnitService;

@Configuration
public class JavaArchUnitBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;

  public JavaArchUnitBeanConfiguration(ProjectRepository projectRepository, BuildToolService buildToolService) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
  }

  @Bean
  public JavaArchUnitService javaArchUnitService() {
    return new JavaArchUnitDomainService(projectRepository, buildToolService);
  }
}
