package tech.jhipster.forge.common.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;

@UnitTest
class DefaultConfigTest {

  @Test
  void shouldNotGetUnknownKey() {
    assertThat(DefaultConfig.get("apero")).isEmpty();
  }

  @Test
  void shouldGetBaseName() {
    assertThat(DefaultConfig.get("baseName")).contains("jhipster");
  }

  @Test
  void shouldGetProjectName() {
    assertThat(DefaultConfig.get("projectName")).contains("JHipster Project");
  }
}
