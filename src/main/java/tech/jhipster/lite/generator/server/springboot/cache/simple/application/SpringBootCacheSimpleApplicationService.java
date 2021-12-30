package tech.jhipster.lite.generator.server.springboot.cache.simple.application;

import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.cache.simple.domain.SpringBootCacheSimpleService;

@Service
public class SpringBootCacheSimpleApplicationService {

  private final SpringBootCacheSimpleService springBootCacheSimpleService;

  public SpringBootCacheSimpleApplicationService(SpringBootCacheSimpleService springBootCacheSimpleService) {
    this.springBootCacheSimpleService = springBootCacheSimpleService;
  }

  public void init(Project project) {
    springBootCacheSimpleService.init(project);
  }
}
