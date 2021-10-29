package tech.jhipster.forge.generator.domain.core;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.forge.generator.domain.core.DefaultConfig.BASE_NAME;
import static tech.jhipster.forge.generator.domain.core.DefaultConfig.PROJECT_NAME;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.generator.domain.core.DefaultConfig;

@UnitTest
class DefaultConfigTest {

  @Test
  void shouldNotGetUnknownKey() {
    assertThat(DefaultConfig.get("apero")).isEmpty();
  }

  @Test
  void shouldGetBaseName() {
    assertThat(DefaultConfig.get(BASE_NAME)).contains("jhipster");
  }

  @Test
  void shouldGetProjectName() {
    assertThat(DefaultConfig.get(PROJECT_NAME)).contains("JHipster Project");
  }
}
