package tech.jhipster.lite.generator.server.springboot.springcloud.eureka.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class EurekaClient {

  private EurekaClient() {
    // Cannot be instantiated
  }

  public static Dependency springCloudNetflixEurekaClient() {
    return Dependency
      .builder()
      .groupId("org.springframework.cloud")
      .artifactId("spring-cloud-starter-netflix-eureka-client")
      .version("\\${spring-cloud-netflix-eureka-client.version}")
      .build();
  }
}
