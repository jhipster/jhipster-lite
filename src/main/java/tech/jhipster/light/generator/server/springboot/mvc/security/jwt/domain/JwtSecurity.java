package tech.jhipster.light.generator.server.springboot.mvc.security.jwt.domain;

import java.util.HashMap;
import java.util.Map;
import tech.jhipster.light.generator.buildtool.generic.domain.Dependency;

public class JwtSecurity {

  private static final String JJWT_VERSION = "0.11.2";
  private static final String infrastructureConfig = "infrastructure/config";
  private static final String infrastructureRest = "infrastructure/primary/rest";

  public static final String annotationProcessorPatch = "jwt-security-annotation-processor.patch";
  public static final String exceptionTranslatorPatch = "jwt-security-exception-translator.patch";

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
    return Dependency.builder().groupId("io.jsonwebtoken").artifactId("jjwt-api").version("\\${jjwt.version}").build();
  }

  public static Dependency jjwtImplDependency() {
    return Dependency.builder().groupId("io.jsonwebtoken").artifactId("jjwt-impl").version("\\${jjwt.version}").scope("runtime").build();
  }

  public static Dependency jjwtJacksonDependency() {
    return Dependency.builder().groupId("io.jsonwebtoken").artifactId("jjwt-jackson").version("\\${jjwt.version}").scope("runtime").build();
  }

  public static Dependency springSecurityTestDependency() {
    return Dependency.builder().groupId("org.springframework.security").artifactId("spring-security-test").scope("test").build();
  }

  public static Map<String, String> jwtSecurityFiles() {
    Map<String, String> map = new HashMap<>();

    map.put("AuthoritiesConstants.java", "domain");

    map.put("SecurityUtils.java", "application");

    map.put("ApplicationSecurityDefaults.java", infrastructureConfig);
    map.put("ApplicationSecurityProperties.java", infrastructureConfig);
    map.put("CorsFilterConfiguration.java", infrastructureConfig);
    map.put("CorsProperties.java", infrastructureConfig);
    map.put("JWTConfigurer.java", infrastructureConfig);
    map.put("JWTFilter.java", infrastructureConfig);
    map.put("SecurityConfiguration.java", infrastructureConfig);
    map.put("SecurityExceptionTranslator.java", infrastructureConfig);
    map.put("TokenProvider.java", infrastructureConfig);

    map.put("AuthenticationResource.java", infrastructureRest);
    map.put("LoginDTO.java", infrastructureRest);

    return map;
  }

  public static Map<String, String> jwtTestSecurityFiles() {
    Map<String, String> map = new HashMap<>();

    map.put("SecurityUtilsTest.java", "application");

    map.put("ApplicationSecurityPropertiesTest.java", infrastructureConfig);
    map.put("CorsFilterConfigurationIT.java", infrastructureConfig);
    map.put("JWTFilterTest.java", infrastructureConfig);
    map.put("TokenProviderTest.java", infrastructureConfig);

    map.put("AuthenticationResourceIT.java", infrastructureRest);
    map.put("LoginDTOTest.java", infrastructureRest);

    return map;
  }
}
