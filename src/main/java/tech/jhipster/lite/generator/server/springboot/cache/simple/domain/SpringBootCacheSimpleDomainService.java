package tech.jhipster.lite.generator.server.springboot.cache.simple.domain;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.cache.common.domain.SpringBootCacheService;

public class SpringBootCacheSimpleDomainService implements SpringBootCacheSimpleService {

  private final SpringBootCacheService springBootCacheService;

  public SpringBootCacheSimpleDomainService(SpringBootCacheService springBootCacheService) {
    this.springBootCacheService = springBootCacheService;
  }

  @Override
  public void init(Project project) {
    springBootCacheService.addDependencies(project);
    springBootCacheService.addEnableCaching(project);
  }
}
