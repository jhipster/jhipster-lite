package tech.jhipster.lite.generator.server.springboot.consul.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Consul {

  public static final String BOOTSTRAP_PROPERTIES = "bootstrap.properties";
  public static final String BOOTSTRAP_FAST_PROPERTIES = "bootstrap-fast.properties";

  public static final String NEEDLE_BOOTSTRAP_PROPERTIES = "# jhipster-needle-bootstrap-properties";
  public static final String NEEDLE_BOOTSTRAP_FAST_PROPERTIES = "# jhipster-needle-bootstrap-fast-properties";
  public static final String NEEDLE_BOOTSTRAP_TEST_PROPERTIES = "# jhipster-needle-bootstrap-test-properties";

  private static final String SPRING_CLOUD = "org.springframework.cloud";

  private static final String SPRING_CLOUD_VERSION = "2021.0.0";

  private Consul() {}

  public static String getSpringCloudVersion() {
    return SPRING_CLOUD_VERSION;
  }

  public static Dependency springCloudDependencyManagement() {
    return Dependency
      .builder()
      .groupId(SPRING_CLOUD)
      .artifactId("spring-cloud-dependencies")
      .version("\\${spring-cloud.version}")
      .type("pom")
      .scope("import")
      .build();
  }

  public static Dependency springCloudBootstrapDependency() {
    return Dependency.builder().groupId(SPRING_CLOUD).artifactId("spring-cloud-starter-bootstrap").build();
  }

  public static Dependency springCloudConsulDiscoveryDependency() {
    return Dependency.builder().groupId(SPRING_CLOUD).artifactId("spring-cloud-starter-consul-discovery").build();
  }

  public static Dependency springCloudConsulConfigDependency() {
    return Dependency.builder().groupId(SPRING_CLOUD).artifactId("spring-cloud-starter-consul-config").build();
  }
}
