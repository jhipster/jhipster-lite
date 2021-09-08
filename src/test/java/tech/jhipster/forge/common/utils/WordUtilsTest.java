package tech.jhipster.forge.common.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;

@UnitTest
class WordUtilsTest {

  @Nested
  class KebabCase {

    @Test
    void shouldKebabCase() {
      assertThat(WordUtils.kebabCase("JhipsterForge")).isEqualTo("jhipster-forge");
    }

    @Test
    void shouldKebabCaseWhenStartedWith2UpperCase() {
      assertThat(WordUtils.kebabCase("JHipsterForge")).isEqualTo("j-hipster-forge");
    }

    @Test
    void shouldNotKebabCaseForNull() {
      assertThatThrownBy(() -> WordUtils.kebabCase(null))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("value");
    }

    @Test
    void shouldNotKebabForBlank() {
      assertThatThrownBy(() -> WordUtils.kebabCase(" "))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("value");
    }
  }
}
