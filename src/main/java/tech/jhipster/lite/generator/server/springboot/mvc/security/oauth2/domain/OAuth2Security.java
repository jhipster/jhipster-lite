package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class OAuth2Security {

  private static final String SPRINGBOOT_PACKAGE = "org.springframework.boot";

  private static final String STARTER_SECURITY = "spring-boot-starter-security";
  private static final String STARTER_OAUTH2_CLIENT = "spring-boot-starter-oauth2-client";
  private static final String STARTER_OAUTH2_RESOURCE_SERVER = "spring-boot-starter-oauth2-resource-server";

  private OAuth2Security() {}

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

  public static Dependency springBootStarterOAuth2ResourceServerDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId(STARTER_OAUTH2_RESOURCE_SERVER).build();
  }
}
