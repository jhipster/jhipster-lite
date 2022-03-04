package tech.jhipster.lite.generator.server.springboot.springcloud.consul.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Consul {

  public static final String BOOTSTRAP_PROPERTIES = "bootstrap.properties";
  public static final String BOOTSTRAP_LOCAL_PROPERTIES = "bootstrap-local.properties";

  private static final String DOCKER_CONSUL_IMAGE = "consul:1.11.1";
  private static final String DOCKER_CONSUL_CONFIG_LOADER_IMAGE = "jhipster/consul-config-loader:v0.4.1";
  private static final String SPRING_CLOUD = "org.springframework.cloud";

  private Consul() {}

  public static String getDockerConsulImage() {
    return DOCKER_CONSUL_IMAGE;
  }

  public static String getDockerConsulConfigLoaderImage() {
    return DOCKER_CONSUL_CONFIG_LOADER_IMAGE;
  }

  public static Dependency springCloudConsulDiscoveryDependency() {
    return Dependency.builder().groupId(SPRING_CLOUD).artifactId("spring-cloud-starter-consul-discovery").build();
  }

  public static Dependency springCloudConsulConfigDependency() {
    return Dependency.builder().groupId(SPRING_CLOUD).artifactId("spring-cloud-starter-consul-config").build();
  }
}
