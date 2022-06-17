package tech.jhipster.lite.generator.module.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.MissingMandatoryValueException;
import tech.jhipster.lite.error.domain.StringTooLongException;

@UnitTest
class JHipsterTagTest {

  @Test
  void shouldIsIdentical() {
    JHipsterModuleTag tag = new JHipsterModuleTag("mytag");
    assertThat(tag.tag()).isEqualTo("mytag");
  }

  @Test
  void shouldNotBeValidWithNull() {
    assertThatThrownBy(() -> new JHipsterModuleTag(null)).isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldNotBeValidWithWhitespace() {
    assertThatThrownBy(() -> new JHipsterModuleTag("my tag")).isInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldNotBeValidIfTooLong() {
    var stringTooLong = RandomStringUtils.randomAlphabetic(16);
    assertThatThrownBy(() -> new JHipsterModuleTag(stringTooLong)).isInstanceOf(StringTooLongException.class);
  }

  @ParameterizedTest
  @ValueSource(strings = { "MyTag", "my_tag", "myTag123" })
  void shouldNotBuildInvalidTag(String tag) {
    assertThatThrownBy(() -> new JHipsterModuleTag(tag)).isExactlyInstanceOf(InvalidJHipsterModuleTagException.class);
  }
}
