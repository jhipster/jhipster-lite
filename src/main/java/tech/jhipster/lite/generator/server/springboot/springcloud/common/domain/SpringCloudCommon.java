package tech.jhipster.lite.generator.server.springboot.springcloud.common.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class SpringCloudCommon {

  public static final String JHIPSTER_REGISTRY_DOCKER_IMAGE_NAME = "jhipster/jhipster-registry";
  public static final String SPRINGFRAMEWORK_CLOUD_GROUP_ID = "org.springframework.cloud";

  private SpringCloudCommon() {
    // Cannot be instantiated
  }

  public static Dependency springCloudDependencyManagement() {
    return Dependency
      .builder()
      .groupId(SPRINGFRAMEWORK_CLOUD_GROUP_ID)
      .artifactId("spring-cloud-dependencies")
      .version("\\${spring-cloud.version}")
      .type("pom")
      .scope("import")
      .build();
  }

  public static Dependency springCloudStarterBootstrap() {
    return Dependency.builder().groupId(SPRINGFRAMEWORK_CLOUD_GROUP_ID).artifactId("spring-cloud-starter-bootstrap").build();
  }
}
