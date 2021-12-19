package tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.domain;

import java.util.HashMap;
import java.util.Map;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class JwtSecurity {

  private static final String JJWT_VERSION = "0.11.2";
  private static final String INFRASTRUCTURE_CONFIG = "infrastructure/config";
  private static final String INFRASTRUCTURE_PRIMARY_REST = "infrastructure/primary/rest";

  public static final String ANNOTATION_PROCESSOR_PATCH = "jwt-security-annotation-processor.patch";
  public static final String JSONWEBTOKEN_PACKAGE = "io.jsonwebtoken";
  public static final String JJWT_VERSION_PROPERTY = "\\${jjwt.version}";

  private JwtSecurity() {}

  public static String jjwtVersion() {
    return JJWT_VERSION;
  }

  public static Dependency springBootStarterSecurityDependency() {
    return Dependency.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-security").build();
  }

  public static Dependency springBootConfigurationProcessor() {
    return Dependency
      .builder()
      .groupId("org.springframework.boot")
      .artifactId("spring-boot-configuration-processor")
      .scope("provided")
      .build();
  }

  public static Dependency jjwtApiDependency() {
    return Dependency.builder().groupId(JSONWEBTOKEN_PACKAGE).artifactId("jjwt-api").version(JJWT_VERSION_PROPERTY).build();
  }

  public static Dependency jjwtImplDependency() {
    return Dependency
      .builder()
      .groupId(JSONWEBTOKEN_PACKAGE)
      .artifactId("jjwt-impl")
      .version(JJWT_VERSION_PROPERTY)
      .scope("runtime")
      .build();
  }

  public static Dependency jjwtJacksonDependency() {
    return Dependency
      .builder()
      .groupId(JSONWEBTOKEN_PACKAGE)
      .artifactId("jjwt-jackson")
      .version(JJWT_VERSION_PROPERTY)
      .scope("runtime")
      .build();
  }

  public static Dependency springSecurityTestDependency() {
    return Dependency.builder().groupId("org.springframework.security").artifactId("spring-security-test").scope("test").build();
  }

  public static Map<String, String> jwtSecurityFiles() {
    Map<String, String> map = new HashMap<>();

    map.put("AuthoritiesConstants.java", "domain");

    map.put("SecurityUtils.java", "application");

    map.put("ApplicationSecurityDefaults.java", INFRASTRUCTURE_CONFIG);
    map.put("ApplicationSecurityProperties.java", INFRASTRUCTURE_CONFIG);
    map.put("CorsFilterConfiguration.java", INFRASTRUCTURE_CONFIG);
    map.put("CorsProperties.java", INFRASTRUCTURE_CONFIG);
    map.put("JWTConfigurer.java", INFRASTRUCTURE_CONFIG);
    map.put("JWTFilter.java", INFRASTRUCTURE_CONFIG);
    map.put("SecurityConfiguration.java", INFRASTRUCTURE_CONFIG);
    map.put("SecurityExceptionTranslator.java", INFRASTRUCTURE_CONFIG);
    map.put("TokenProvider.java", INFRASTRUCTURE_CONFIG);

    map.put("AuthenticationResource.java", INFRASTRUCTURE_PRIMARY_REST);
    map.put("LoginDTO.java", INFRASTRUCTURE_PRIMARY_REST);

    return map;
  }

  public static Map<String, String> jwtTestSecurityFiles() {
    Map<String, String> map = new HashMap<>();

    map.put("SecurityUtilsTest.java", "application");

    map.put("ApplicationSecurityPropertiesTest.java", INFRASTRUCTURE_CONFIG);
    map.put("CorsFilterConfigurationIT.java", INFRASTRUCTURE_CONFIG);
    map.put("JWTFilterTest.java", INFRASTRUCTURE_CONFIG);
    map.put("TokenProviderTest.java", INFRASTRUCTURE_CONFIG);

    map.put("AuthenticationResourceIT.java", INFRASTRUCTURE_PRIMARY_REST);
    map.put("LoginDTOTest.java", INFRASTRUCTURE_PRIMARY_REST);

    return map;
  }
}
