package tech.jhipster.lite.generator.module.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;

@UnitTest
class JHipsterTagsTest {

  @Test
  void shouldIsIdentical() {
    JHipsterModuleTags module = JHipsterModuleTags.builder(JHipsterModulesFixture.emptyModuleBuilder()).add("mytag").build();
    assertThat(module.get()).isNotEmpty().containsOnly(new JHipsterModuleTag("mytag"));
  }

  @Test
  void shouldNotBeValidWithStringNull() {
    JHipsterModuleTags.JHipsterModuleTagsBuilder builder = JHipsterModuleTags.builder(JHipsterModulesFixture.emptyModuleBuilder());
    assertThatThrownBy(() -> builder.add((String) null).build()).isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldNotBeValidWithModuleTagNull() {
    JHipsterModuleTags.JHipsterModuleTagsBuilder builder = JHipsterModuleTags.builder(JHipsterModulesFixture.emptyModuleBuilder());
    assertThatThrownBy(() -> builder.add((JHipsterModuleTag) null).build()).isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldNotBeValidWithWhitespace() {
    JHipsterModuleTags.JHipsterModuleTagsBuilder builder = JHipsterModuleTags.builder(JHipsterModulesFixture.emptyModuleBuilder());
    assertThatThrownBy(() -> builder.add("my tag").build()).isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void tagsShouldNotBeModifiable() {
    JHipsterModuleTags module = JHipsterModuleTags
      .builder(JHipsterModulesFixture.emptyModuleBuilder())
      .add("spring")
      .add("database")
      .add(new JHipsterModuleTag("postgresql"))
      .build();
    assertThatThrownBy(() -> module.get().add(new JHipsterModuleTag("unauthorized"))).isInstanceOf(UnsupportedOperationException.class);
  }
}
