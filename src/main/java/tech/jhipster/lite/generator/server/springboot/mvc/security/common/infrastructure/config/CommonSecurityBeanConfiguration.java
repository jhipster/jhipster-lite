package tech.jhipster.lite.generator.server.springboot.mvc.security.common.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.mvc.security.common.domain.CommonSecurityDomainService;
import tech.jhipster.lite.generator.server.springboot.mvc.security.common.domain.CommonSecurityService;

@Configuration
public class CommonSecurityBeanConfiguration {

  private final ProjectRepository projectRepository;

  public CommonSecurityBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public CommonSecurityService commonSecurityService() {
    return new CommonSecurityDomainService(projectRepository);
  }
}
