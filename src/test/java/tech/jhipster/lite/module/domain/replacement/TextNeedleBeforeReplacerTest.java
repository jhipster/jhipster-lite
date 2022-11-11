package tech.jhipster.lite.module.domain.replacement;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.module.domain.replacement.ReplacementCondition.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tech.jhipster.lite.UnitTest;

@UnitTest
class TextNeedleBeforeReplacerTest {

  @Test
  void shouldNotMatchUnknownText() {
    assertThat(new TextNeedleBeforeReplacer(always(), "unknown").notMatchIn("content")).isTrue();
  }

  @Test
  void shouldMatchKnownText() {
    assertThat(new TextNeedleBeforeReplacer(always(), "known").notMatchIn("unknown")).isFalse();
  }

  @Test
  void shouldNotReplaceUnknownNeedle() {
    TextNeedleBeforeReplacer replacer = new TextNeedleBeforeReplacer(always(), "<!-- needle !-->");

    String updatedContent = replacer.replacement().apply("<root />", "<element />");

    assertThat(updatedContent).isEqualTo("<root />");
  }

  @ParameterizedTest
  @MethodSource("data")
  void shouldInsertTextLine(String content, String replacement, String result) {
    TextNeedleBeforeReplacer replacer = new TextNeedleBeforeReplacer(always(), "<!-- needle !-->");

    String updatedContent = replacer.replacement().apply(content, replacement);

    assertThat(updatedContent).isEqualTo(result);
  }

  static Stream<Arguments> data() {
    return Stream.of(
      Arguments.of(
        """
            <!-- needle !-->
            """,
        "<element />",
        """
        <element />
        <!-- needle !-->
        """
      ),
      Arguments.of(
        """
            <root>
            <!-- needle !-->
            </root>
            """,
        "<element />",
        """
        <root>
        <element />
        <!-- needle !-->
        </root>
        """
      ),
      Arguments.of(
        """
            <root>
              <!-- needle !-->

            </root>
            """,
        "<element />",
        """
        <root>
        <element />
          <!-- needle !-->

        </root>
        """
      ),
      Arguments.of(
        """
            <root>
              <!-- needle !-->


              <!-- needle !-->
              <!-- needle !--> with trailling text
            </root>
            """,
        "<element />",
        """
        <root>
        <element />
          <!-- needle !-->


        <element />
          <!-- needle !-->
        <element />
          <!-- needle !--> with trailling text
        </root>
        """
      )
    );
  }

  @Test
  void shouldGetTextAsMatcher() {
    assertThat(new TextNeedleBeforeReplacer(always(), "known").searchMatcher()).isEqualTo("known");
  }
}
