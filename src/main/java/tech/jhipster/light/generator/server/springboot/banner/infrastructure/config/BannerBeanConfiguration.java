package tech.jhipster.light.generator.server.springboot.banner.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.light.generator.project.domain.ProjectRepository;
import tech.jhipster.light.generator.server.springboot.banner.domain.BannerDomainService;
import tech.jhipster.light.generator.server.springboot.banner.domain.BannerService;

@Configuration
public class BannerBeanConfiguration {

  private final ProjectRepository projectRepository;

  public BannerBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public BannerService bannerService() {
    return new BannerDomainService(projectRepository);
  }
}
