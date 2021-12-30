package tech.jhipster.lite.generator.server.springboot.cache.common.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class SpringBootCache {

  public static final String SPRINGBOOT_PACKAGE = "org.springframework.boot";

  private SpringBootCache() {}

  public static Dependency springBootStarterCacheDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId("spring-boot-starter-cache").build();
  }
}
