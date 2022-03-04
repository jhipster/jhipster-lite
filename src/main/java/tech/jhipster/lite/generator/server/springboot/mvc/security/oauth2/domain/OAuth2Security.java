package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.units.qual.K;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.technical.infrastructure.primary.cors.CorsFilterConfiguration;
import tech.jhipster.lite.technical.infrastructure.primary.cors.CorsProperties;

public class OAuth2Security {

  private static final String INFRASTRUCTURE_CONFIG = "infrastructure/config";

  public static final OAuth2Provider DEFAULT_PROVIDER = OAuth2Provider.KEYCLOAK;

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

  public static Dependency springSecurityTestDependency() {
    return Dependency.builder().groupId("org.springframework.security").artifactId("spring-security-test").scope("test").build();
  }

  public static Dependency springBootStarterOAuth2ClientDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId(STARTER_OAUTH2_CLIENT).build();
  }

  public static Dependency springBootStarterOAuth2ResourceServerDependency() {
    return Dependency.builder().groupId(SPRINGBOOT_PACKAGE).artifactId(STARTER_OAUTH2_RESOURCE_SERVER).build();
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
}
