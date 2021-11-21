package tech.jhipster.light.generator.server.springboot.web.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.light.UnitTest;
import tech.jhipster.light.generator.buildtool.generic.domain.Dependency;

@UnitTest
class SpringBootWebTest {

  @Test
  void shouldGetSpringfoxVersionVersion() {
    assertThat(SpringBootWeb.springfoxVersion()).isEqualTo("3.0.0");
  }

  @Test
  void shouldSpringBootStarterWebDependency() {
    Dependency dependency = SpringBootWeb.springBootStarterWebDependency();

    assertThat(dependency.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(dependency.getArtifactId()).isEqualTo("spring-boot-starter-web");
    assertThat(dependency.getVersion()).isEmpty();
    assertThat(dependency.getScope()).isEmpty();
  }

  @Test
  void shouldTomcatDependency() {
    Dependency dependency = SpringBootWeb.tomcatDependency();

    assertThat(dependency.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(dependency.getArtifactId()).isEqualTo("spring-boot-starter-tomcat");
    assertThat(dependency.getVersion()).isEmpty();
    assertThat(dependency.getScope()).isEmpty();
  }

  @Test
  void shouldUndertowDependency() {
    Dependency dependency = SpringBootWeb.undertowDependency();

    assertThat(dependency.getGroupId()).isEqualTo("org.springframework.boot");
    assertThat(dependency.getArtifactId()).isEqualTo("spring-boot-starter-undertow");
    assertThat(dependency.getVersion()).isEmpty();
    assertThat(dependency.getScope()).isEmpty();
  }

  @Test
  void shouldSpringfoxDependency() {
    Dependency dependency = SpringBootWeb.springfoxDependency();

    assertThat(dependency.getGroupId()).isEqualTo("io.springfox");
    assertThat(dependency.getArtifactId()).isEqualTo("springfox-boot-starter");
    assertThat(dependency.getVersion()).contains("\\${springfox.version}");
    assertThat(dependency.getScope()).isEmpty();
  }
}
