package tech.jhipster.lite.generator.module.domain;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.generator.module.domain.JHipsterModulesFixture.*;

import java.util.Map;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class JHipsterModuleContextTest {

  @Test
  void shouldGetDefaultContext() {
    Map<String, Object> context = JHipsterModuleContext.builder(emptyModuleBuilder()).build().get();

    assertThat(context)
      .hasSize(4)
      .contains(
        entry("baseName", "jhipster"),
        entry("projectName", "JHipster Project"),
        entry("packageName", "com.mycompany.myapp"),
        entry("prettierDefaultIndent", 2)
      );
  }
}
