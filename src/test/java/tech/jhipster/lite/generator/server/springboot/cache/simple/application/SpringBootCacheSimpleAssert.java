package tech.jhipster.lite.generator.server.springboot.cache.simple.application;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.cache.common.application.SpringBootCacheAssert;

public class SpringBootCacheSimpleAssert {

  public static void assertInit(Project project) {
    SpringBootCacheAssert.assertDependencies(project);
    SpringBootCacheAssert.assertEnableCaching(project);
  }
}
