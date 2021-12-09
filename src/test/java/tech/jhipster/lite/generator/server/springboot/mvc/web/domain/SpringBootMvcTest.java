package tech.jhipster.lite.generator.server.springboot.mvc.web.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

@UnitTest
class SpringBootMvcTest {

  @Test
  void shouldGetSpringfoxVersionVersion() {
    assertThat(SpringBootMvc.springfoxVersion()).isEqualTo("3.0.0");
  }

  @Test
  void shouldGetProblemSpringVersion() {
    assertThat(SpringBootMvc.problemSpringVersion()).isEqualTo("0.27.0");
  }

  @Test
  void shouldSpringBootStarterWebDependency() {
    Dependency dependency = SpringBootMvc.springBootStarterWebDependency();

    assertThat(dependency.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(dependency.getArtifactId()).isEqualTo("spring-boot-starter-web");
    assertThat(dependency.getVersion()).isEmpty();
    assertThat(dependency.getScope()).isEmpty();
  }

  @Test
  void shouldTomcatDependency() {
    Dependency dependency = SpringBootMvc.tomcatDependency();

    assertThat(dependency.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(dependency.getArtifactId()).isEqualTo("spring-boot-starter-tomcat");
    assertThat(dependency.getVersion()).isEmpty();
    assertThat(dependency.getScope()).isEmpty();
  }

  @Test
  void shouldUndertowDependency() {
    Dependency dependency = SpringBootMvc.undertowDependency();

    assertThat(dependency.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(dependency.getArtifactId()).isEqualTo("spring-boot-starter-undertow");
    assertThat(dependency.getVersion()).isEmpty();
    assertThat(dependency.getScope()).isEmpty();
  }

  @Test
  void shouldSpringfoxDependency() {
    Dependency dependency = SpringBootMvc.springfoxDependency();

    assertThat(dependency.getGroupId()).isEqualTo("io.springfox");
    assertThat(dependency.getArtifactId()).isEqualTo("springfox-boot-starter");
    assertThat(dependency.getVersion()).contains("\\${springfox.version}");
    assertThat(dependency.getScope()).isEmpty();
  }

  @Test
  void shouldProblemSpringDependency() {
    Dependency dependency = SpringBootMvc.problemSpringDependency();

    assertThat(dependency.getGroupId()).isEqualTo("org.zalando");
    assertThat(dependency.getArtifactId()).isEqualTo("problem-spring-web");
    assertThat(dependency.getVersion()).contains("\\${problem-spring-web.version}");
    assertThat(dependency.getScope()).isEmpty();
  }

  @Test
  void shouldSpringBootStarterValidation() {
    Dependency dependency = SpringBootMvc.springBootStarterValidation();

    assertThat(dependency.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(dependency.getArtifactId()).isEqualTo("spring-boot-starter-validation");
    assertThat(dependency.getVersion()).isEmpty();
    assertThat(dependency.getScope()).isEmpty();
  }
}
