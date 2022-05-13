package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Gateway {

  private static final String SPRING_CLOUD = "org.springframework.cloud";

  private Gateway() {
    // Cannot be instantiated
  }

  public static Dependency springCloudGateway() {
    return Dependency.builder().groupId(SPRING_CLOUD).artifactId("spring-cloud-starter-gateway").build();
  }
}
