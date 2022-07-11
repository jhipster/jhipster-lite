package tech.jhipster.lite.generator.server.springboot.cache.simple.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;
import static tech.jhipster.lite.generator.server.springboot.cache.simple.application.SpringBootCacheSimpleAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;
import tech.jhipster.lite.module.infrastructure.secondary.TestJHipsterModules;

@IntegrationTest
class SpringBootCacheSimpleApplicationServiceIT {

  @Autowired
  private SpringBootCacheSimpleApplicationService springBootCacheSimpleApplicationService;

  @Autowired
  private SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "foo");

    TestJHipsterModules.applyInit(project);

    TestJHipsterModules.applyMaven(project);
    springBootApplicationService.init(project);

    springBootCacheSimpleApplicationService.init(project);

    assertInit(project);
  }
}
