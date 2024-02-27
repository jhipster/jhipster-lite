package tech.jhipster.lite.module.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static tech.jhipster.lite.module.domain.JHipsterModulesFixture.emptyModuleBuilder;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tech.jhipster.lite.Logs;
import tech.jhipster.lite.LogsSpy;
import tech.jhipster.lite.LogsSpyExtension;
import tech.jhipster.lite.UnitTest;

@UnitTest
@ExtendWith(LogsSpyExtension.class)
class JHipsterModuleContextTest {

  @Logs
  private LogsSpy logs;

  @Test
  void shouldGetDefaultContext() {
    Map<String, Object> context = JHipsterModuleContext.builder(emptyModuleBuilder()).build().get();

    assertThat(context)
      .hasSize(6)
      .contains(
        entry("baseName", "jhipster"),
        entry("projectName", "JHipster Project"),
        entry("packageName", "com.mycompany.myapp"),
        entry("serverPort", 8080),
        entry("indentSize", 2),
        entry("javaVersion", "21")
      );
  }
}
