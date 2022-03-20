package tech.jhipster.lite.generator.server.springboot.springcloud.consul.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Consul {

  public static final String BOOTSTRAP_PROPERTIES = "bootstrap.properties";
  public static final String BOOTSTRAP_LOCAL_PROPERTIES = "bootstrap-local.properties";

  private static final String DOCKER_CONSUL_IMAGE_NAME = "consul";
  private static final String DOCKER_CONSUL_CONFIG_LOADER_IMAGE_NAME = "jhipster/consul-config-loader";
  private static final String SPRING_CLOUD = "org.springframework.cloud";

  private Consul() {}

  public static String getDockerConsulImageName() {
    return DOCKER_CONSUL_IMAGE_NAME;
  }

  public static String getDockerConsulConfigLoaderImageName() {
    return DOCKER_CONSUL_CONFIG_LOADER_IMAGE_NAME;
  }

  public static Dependency springCloudConsulDiscoveryDependency() {
    return Dependency.builder().groupId(SPRING_CLOUD).artifactId("spring-cloud-starter-consul-discovery").build();
  }

  public static Dependency springCloudConsulConfigDependency() {
    return Dependency.builder().groupId(SPRING_CLOUD).artifactId("spring-cloud-starter-consul-config").build();
  }
}
