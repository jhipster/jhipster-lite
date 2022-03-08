package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import java.util.HashMap;
import java.util.Map;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class OAuth2Security {

  private static final String INFRASTRUCTURE_CONFIG = "infrastructure/config";

  private static final String DOCKER_KEYCLOAK_IMAGE = "jboss/keycloak";
  private static final String DOCKER_KEYCLOAK_VERSION = "16.1.0";

  private static final String SPRINGBOOT_PACKAGE = "org.springframework.boot";

  private static final String STARTER_SECURITY = "spring-boot-starter-security";
  private static final String STARTER_OAUTH2_CLIENT = "spring-boot-starter-oauth2-client";
  private static final String STARTER_OAUTH2_RESOURCE_SERVER = "spring-boot-starter-oauth2-resource-server";

  private OAuth2Security() {}

  public static String getDockerKeycloakImage() {
    return DOCKER_KEYCLOAK_IMAGE + ":" + DOCKER_KEYCLOAK_VERSION;
  }

  public static String getDockerKeycloakVersion() {
    return DOCKER_KEYCLOAK_VERSION;
  }

  public static Dependency springBootStarterSecurityDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId(STARTER_SECURITY).build();
  }

  public static Dependency springBootStarterOAuth2ClientDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId(STARTER_OAUTH2_CLIENT).build();
  }

  public static Dependency springBootStarterOAuth2ResourceServerDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId(STARTER_OAUTH2_RESOURCE_SERVER).build();
  }

  public static Dependency springSecurityTestDependency() {
    return Dependency.builder().groupId("org.springframework.security").artifactId("spring-security-test").scope("test").build();
  }

  public static Map<String, String> oauth2SecurityFiles() {
    Map<String, String> map = new HashMap<>();

    map.put("SecurityUtils.java", "application");

    map.put("AuthoritiesConstants.java", "domain");
    map.put("ApplicationSecurityDefaults.java", "domain");

    map.put("ApplicationSecurityProperties.java", INFRASTRUCTURE_CONFIG);
    map.put("AudienceValidator.java", INFRASTRUCTURE_CONFIG);
    map.put("CustomClaimConverter.java", INFRASTRUCTURE_CONFIG);
    map.put("JwtGrantedAuthorityConverter.java", INFRASTRUCTURE_CONFIG);
    map.put("OAuth2Configuration.java", INFRASTRUCTURE_CONFIG);
    map.put("SecurityConfiguration.java", INFRASTRUCTURE_CONFIG);

    return map;
  }

  public static Map<String, String> oauth2TestSecurityFiles() {
    Map<String, String> map = new HashMap<>();

    map.put("SecurityUtilsTest.java", "application");

    map.put("ApplicationSecurityPropertiesTest.java", INFRASTRUCTURE_CONFIG);
    map.put("AudienceValidatorTest.java", INFRASTRUCTURE_CONFIG);
    map.put("CustomClaimConverterIT.java", INFRASTRUCTURE_CONFIG);
    map.put("FakeRequestAttributes.java", INFRASTRUCTURE_CONFIG);
    map.put("JwtGrantedAuthorityConverterTest.java", INFRASTRUCTURE_CONFIG);
    map.put("SecurityConfigurationIT.java", INFRASTRUCTURE_CONFIG);
    map.put("SecurityConfigurationTest.java", INFRASTRUCTURE_CONFIG);
    map.put("TestSecurityConfiguration.java", INFRASTRUCTURE_CONFIG);

    map.put("WithUnauthenticatedMockUser.java", "infrastructure");

    return map;
  }

  public static Map<String, String> properties() {
    return Map.of(
      "spring.security.oauth2.client.provider.oidc.issuer-uri",
      "http://localhost:9080/auth/realms/jhipster",
      "spring.security.oauth2.client.registration.oidc.client-id",
      "web_app",
      "spring.security.oauth2.client.registration.oidc.client-secret",
      "web_app",
      "spring.security.oauth2.client.registration.oidc.scope",
      "openid,profile,email",
      "application.security.oauth2.audience",
      "account,api://default"
    );
  }

  public static Map<String, String> propertiesForTests() {
    return Map.of(
      "spring.main.allow-bean-definition-overriding",
      "true",
      "spring.security.oauth2.client.provider.oidc.issuer-uri",
      "http://DO_NOT_CALL:9080/auth/realms/jhipster"
    );
  }
}
