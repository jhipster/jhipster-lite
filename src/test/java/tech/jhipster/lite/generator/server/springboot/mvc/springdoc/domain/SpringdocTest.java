package tech.jhipster.lite.generator.server.springboot.mvc.springdoc.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

@UnitTest
class SpringdocTest {

  @Test
  void shouldReturnSpringdocDependency() {
    Dependency expectedDependency = Dependency
      .builder()
      .groupId("org.springdoc")
      .artifactId("springdoc-openapi-ui")
      .version("\\${springdoc-openapi-ui.version}")
      .build();

    assertThat(Springdoc.springdocDependency()).usingRecursiveComparison().isEqualTo(expectedDependency);
  }
}
