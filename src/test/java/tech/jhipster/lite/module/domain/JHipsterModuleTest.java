package tech.jhipster.lite.generator.module.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class JHipsterModuleTest {

  @Test
  void shouldTagExist() {
    var module = JHipsterModulesFixture.module();
    assertThat(module.tags()).isNotEmpty().contains(new JHipsterModuleTag("spring"), new JHipsterModuleTag("database"));
  }

  @Test
  void shouldTagNotBeModifiable() {
    var tags = JHipsterModulesFixture.module().tags();
    var tag = new JHipsterModuleTag("node");
    assertThatThrownBy(() -> tags.add(tag)).isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  void shouldTagNotNullAndEmpty() {
    var module = JHipsterModulesFixture.emptyModuleBuilder().build();
    assertThat(module.tags()).isNotNull();
    assertThat(module.tags()).isEmpty();
  }
}
