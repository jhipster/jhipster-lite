package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class OAuth2Security {

  public static final OAuth2Provider DEFAULT_PROVIDER = OAuth2Provider.KEYCLOAK;

  private static final String DOCKER_KEYCLOAK_IMAGE = "jboss/keycloak";
  private static final String DOCKER_KEYCLOAK_VERSION = "16.1.0";

  private static final String SPRINGBOOT_PACKAGE = "org.springframework.boot";

  private static final String STARTER_SECURITY = "spring-boot-starter-security";
  private static final String STARTER_OAUTH2_CLIENT = "spring-boot-starter-oauth2-client";

  private OAuth2Security() {}

  public static String getDockerKeycloakImage() {
    return DOCKER_KEYCLOAK_IMAGE + ":" + DOCKER_KEYCLOAK_VERSION;
  }

  public static String getDockerKeycloakVersion() {
    return DOCKER_KEYCLOAK_VERSION;
  }

  // TODO security commons
  public static Dependency springBootStarterSecurityDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId(STARTER_SECURITY).build();
  }

  // TODO security commons
  public static Dependency springSecurityTestDependency() {
    return Dependency.builder().groupId("org.springframework.security").artifactId("spring-security-test").scope("test").build();
  }

  public static Dependency springBootStarterOAuth2ClientDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId(STARTER_OAUTH2_CLIENT).build();
  }
}
