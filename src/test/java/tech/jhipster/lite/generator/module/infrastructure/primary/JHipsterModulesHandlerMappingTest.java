package tech.jhipster.lite.generator.module.infrastructure.primary;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.generator.module.infrastructure.primary.JHipsterModulesResourceFixture.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class JHipsterModulesHandlerMappingTest {

  @Test
  void shouldNotBuildWithDuplicatedSlug() {
    JHipsterModuleResource resource = defaultModuleResourceBuilder().slug("dummy").build();

    assertThatThrownBy(() -> jhipsterModuleMapping(resource, resource)).isExactlyInstanceOf(DuplicatedSlugException.class);
  }
}
