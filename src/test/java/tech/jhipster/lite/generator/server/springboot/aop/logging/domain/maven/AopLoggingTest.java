package tech.jhipster.lite.generator.server.springboot.aop.logging.domain.maven;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Parent;
import tech.jhipster.lite.generator.buildtool.maven.domain.Maven;

@UnitTest
class AopLoggingTest {

  @Test
  void shouldGetParent() {
    // @formatter:off
    String expected =
      "<parent>" + System.lineSeparator() +
      "    <groupId>org.springframework.boot</groupId>" + System.lineSeparator() +
      "    <artifactId>spring-boot-starter-parent</artifactId>" + System.lineSeparator() +
      "    <version>2.5.3</version>" + System.lineSeparator() +
      "    <relativePath/>" + System.lineSeparator() +
      "  </parent>";
    // @formatter:on
    Parent parent = Parent.builder().groupId("org.springframework.boot").artifactId("spring-boot-starter-parent").version("2.5.3").build();

    assertThat(Maven.getParent(parent)).isEqualTo(expected);
  }
}
