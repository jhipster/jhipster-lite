package tech.jhipster.light.generator.server.springboot.banner.infrastructure.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.light.generator.project.domain.ProjectRepository;
import tech.jhipster.light.generator.server.springboot.banner.domain.BannerDomainService;
import tech.jhipster.light.generator.server.springboot.banner.domain.BannerService;

@Configuration
public class BannerServiceBeanConfiguration {

  private final ProjectRepository projectRepository;

  public BannerServiceBeanConfiguration(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Bean
  public BannerService bannerService() {
    return new BannerDomainService(projectRepository);
  }
}
