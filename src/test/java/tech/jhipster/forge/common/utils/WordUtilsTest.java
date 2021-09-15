package tech.jhipster.forge.common.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;
import tech.jhipster.forge.error.domain.UnauthorizedValueException;

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

  @Nested
  class Indent {

    @Test
    void shouldIndent1Time() {
      assertThat(WordUtils.indent(1)).isEqualTo("  ");
    }

    @Test
    void shouldIndent2Times() {
      assertThat(WordUtils.indent(2)).isEqualTo("    ");
    }

    @Test
    void shouldIndent3Times() {
      assertThat(WordUtils.indent(3)).isEqualTo("      ");
    }

    @Test
    void shouldNotIndentWithNegative() {
      assertThatThrownBy(() -> WordUtils.indent(-1)).isExactlyInstanceOf(UnauthorizedValueException.class).hasMessageContaining("times");
    }

    @Test
    void shouldNotIndentWithZero() {
      assertThatThrownBy(() -> WordUtils.indent(0)).isExactlyInstanceOf(UnauthorizedValueException.class).hasMessageContaining("times");
    }
  }

  @Nested
  class IdentWithSpace {

    @Test
    void shouldIndent1TimeWith4spaces() {
      assertThat(WordUtils.indent(1, 4)).isEqualTo("    ");
    }

    @Test
    void shouldIndent2TimesWith4spaces() {
      assertThat(WordUtils.indent(2, 4)).isEqualTo("        ");
    }

    @Test
    void shouldNotIndentNegativeTimeWith4Spaces() {
      assertThatThrownBy(() -> WordUtils.indent(-1, 4)).isExactlyInstanceOf(UnauthorizedValueException.class).hasMessageContaining("times");
    }

    @Test
    void shouldNotIndent0TimeWith4Spaces() {
      assertThatThrownBy(() -> WordUtils.indent(0, 4)).isExactlyInstanceOf(UnauthorizedValueException.class).hasMessageContaining("times");
    }

    @Test
    void shouldNotIndent2TimesWithNegativeSpace() {
      assertThatThrownBy(() -> WordUtils.indent(2, -1))
        .isExactlyInstanceOf(UnauthorizedValueException.class)
        .hasMessageContaining("spaceNumber");
    }

    @Test
    void shouldNotIndent2TimesWith0space() {
      assertThatThrownBy(() -> WordUtils.indent(2, 0))
        .isExactlyInstanceOf(UnauthorizedValueException.class)
        .hasMessageContaining("spaceNumber");
    }
  }
}
