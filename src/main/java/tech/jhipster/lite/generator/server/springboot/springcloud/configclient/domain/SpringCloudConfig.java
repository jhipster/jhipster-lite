package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class SpringCloudConfig {

  private static final String SPRING_CLOUD_GROUP_ID = "org.springframework.cloud";

  private SpringCloudConfig() {}

  public static Dependency springCloudConfigClient() {
    return Dependency.builder().groupId(SPRING_CLOUD_GROUP_ID).artifactId("spring-cloud-starter-config").build();
  }
}
