package tech.jhipster.lite.generator.module.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class JHipsterModuleTest {

  @Test
  void shouldHaveImmutableTags() {
    var tags = JHipsterModulesFixture.module().tags();
    var tag = new JHipsterModuleTag("node");
    assertThatThrownBy(() -> tags.add(tag)).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  void shouldGetEmptyTagsWithoutTags() {
    var module = JHipsterModulesFixture.emptyModuleBuilder().build();
    assertThat(module.tags()).isNotNull().isEmpty();
  }
}
