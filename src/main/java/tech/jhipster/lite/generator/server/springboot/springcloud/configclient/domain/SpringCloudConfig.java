package tech.jhipster.lite.generator.server.springboot.springcloud.configclient.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class SpringCloudConfig {

  private static final String JHIPSTER_REGISTRY_DOCKER_IMAGE = "jhipster/jhipster-registry:v7.1.0";
  private static final String SPRING_CLOUD_GROUP_ID = "org.springframework.cloud";

  private SpringCloudConfig() {}

  public static Dependency springCloudDependencies() {
    return Dependency
      .builder()
      .groupId(SPRING_CLOUD_GROUP_ID)
      .artifactId("spring-cloud-dependencies")
      .version("\\${spring-cloud.version}")
      .type("pom")
      .scope("import")
      .build();
  }

  public static Dependency springCloudBootstrap() {
    return Dependency.builder().groupId(SPRING_CLOUD_GROUP_ID).artifactId("spring-cloud-starter-bootstrap").build();
  }

  public static Dependency springCloudConfigClient() {
    return Dependency.builder().groupId(SPRING_CLOUD_GROUP_ID).artifactId("spring-cloud-starter-config").build();
  }

  public static String getJhipsterRegistryDockerImage() {
    return JHIPSTER_REGISTRY_DOCKER_IMAGE;
  }
}
