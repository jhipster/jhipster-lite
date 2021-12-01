package tech.jhipster.light.generator.server.springboot.mvc.security.jwt.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.light.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.light.generator.project.domain.ProjectRepository;
import tech.jhipster.light.generator.server.springboot.mvc.security.jwt.domain.JwtSecurityDomainService;
import tech.jhipster.light.generator.server.springboot.mvc.security.jwt.domain.JwtSecurityService;
import tech.jhipster.light.generator.server.springboot.properties.domain.SpringBootPropertiesService;

@Configuration
public class JwtSecurityBeanConfiguration {

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootPropertiesService springBootPropertiesService;

  public JwtSecurityBeanConfiguration(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Bean
  public JwtSecurityService jwtSecurityService() {
    return new JwtSecurityDomainService(projectRepository, buildToolService, springBootPropertiesService);
  }
}
