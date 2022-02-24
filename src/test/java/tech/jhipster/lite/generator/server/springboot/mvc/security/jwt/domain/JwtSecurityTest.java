package tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

@UnitTest
class JwtSecurityTest {

  @Test
  void shouldSpringBootStarterSecurity() {
    Dependency dependency = JwtSecurity.springBootStarterSecurityDependency();
    assertThat(dependency.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(dependency.getArtifactId()).isEqualTo("spring-boot-starter-security");
    assertThat(dependency.getVersion()).isEmpty();
    assertThat(dependency.getScope()).isEmpty();
  }

  @Test
  void shouldJjwtApiDependency() {
    Dependency dependency = JwtSecurity.jjwtApiDependency();
    assertThat(dependency.getGroupId()).isEqualTo("io.jsonwebtoken");
    assertThat(dependency.getArtifactId()).isEqualTo("jjwt-api");
    assertThat(dependency.getVersion()).contains("\\${jjwt.version}");
    assertThat(dependency.getScope()).isEmpty();
  }

  @Test
  void shouldJjwtImplDependency() {
    Dependency dependency = JwtSecurity.jjwtImplDependency();
    assertThat(dependency.getGroupId()).isEqualTo("io.jsonwebtoken");
    assertThat(dependency.getArtifactId()).isEqualTo("jjwt-impl");
    assertThat(dependency.getVersion()).contains("\\${jjwt.version}");
    assertThat(dependency.getScope()).contains("runtime");
  }

  @Test
  void shouldJjwtJacksonDependency() {
    Dependency dependency = JwtSecurity.jjwtJacksonDependency();
    assertThat(dependency.getGroupId()).isEqualTo("io.jsonwebtoken");
    assertThat(dependency.getArtifactId()).isEqualTo("jjwt-jackson");
    assertThat(dependency.getVersion()).contains("\\${jjwt.version}");
    assertThat(dependency.getScope()).contains("runtime");
  }

  @Test
  void shouldSpringSecurityTestDependency() {
    Dependency dependency = JwtSecurity.springSecurityTestDependency();
    assertThat(dependency.getGroupId()).isEqualTo("org.springframework.security");
    assertThat(dependency.getArtifactId()).isEqualTo("spring-security-test");
    assertThat(dependency.getVersion()).isEmpty();
    assertThat(dependency.getScope()).contains("test");
  }

  @Test
  void shouldJwtSecurityFiles() {
    List<String> javaFiles = List.of(
      "AuthoritiesConstants.java",
      "SecurityUtils.java",
      "ApplicationSecurityDefaults.java",
      "ApplicationSecurityProperties.java",
      "CorsFilterConfiguration.java",
      "CorsProperties.java",
      "JWTConfigurer.java",
      "JWTFilter.java",
      "SecurityConfiguration.java",
      "TokenProvider.java"
    );

    assertThat(JwtSecurity.jwtSecurityFiles().keySet()).containsExactlyInAnyOrderElementsOf(javaFiles);
  }

  @Test
  void shouldJwtTestSecurityFiles() {
    List<String> javaTestFiles = List.of(
      "SecurityUtilsTest.java",
      "ApplicationSecurityPropertiesTest.java",
      "CorsFilterConfigurationIT.java",
      "JWTFilterTest.java",
      "TokenProviderTest.java"
    );

    assertThat(JwtSecurity.jwtTestSecurityFiles().keySet()).containsExactlyInAnyOrderElementsOf(javaTestFiles);
  }
}
