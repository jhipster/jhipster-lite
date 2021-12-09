package tech.jhipster.lite.generator.server.springboot.banner.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.banner.domain.BannerDomainService;
import tech.jhipster.lite.generator.server.springboot.banner.domain.BannerService;

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
