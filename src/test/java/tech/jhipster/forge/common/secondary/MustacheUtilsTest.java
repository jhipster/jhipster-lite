package tech.jhipster.forge.common.secondary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.jhipster.forge.common.domain.Constants.TEST_TEMPLATE_RESOURCES;

import com.github.mustachejava.MustacheNotFoundException;
import java.util.Map;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.FileUtils;

@UnitTest
class MustacheUtilsTest {

  @Test
  void shouldTemplate() throws Exception {
    String file = FileUtils.getPath(TEST_TEMPLATE_RESOURCES, "common", "README.md.mustache");

    Chips chips = new Chips("chips");
    String result = MustacheUtils.template(file, chips);

    assertThat(result).isEqualTo("The apero can start when there is some chips\n");
  }

  @Test
  void shouldTemplateWithMap() throws Exception {
    String file = FileUtils.getPath(TEST_TEMPLATE_RESOURCES, "common", "README.md.mustache");

    Map<String, String> chips = Map.of("name", "chips");
    String result = MustacheUtils.template(file, chips);

    assertThat(result).isEqualTo("The apero can start when there is some chips\n");
  }

  @Test
  void shouldNotTemplateWithNonExistingFile() throws Exception {
    String file = FileUtils.getPath(TEST_TEMPLATE_RESOURCES, "common", "README.md.bad.mustache");

    Chips chips = new Chips("chips");
    assertThatThrownBy(() -> MustacheUtils.template(file, chips)).isExactlyInstanceOf(MustacheNotFoundException.class);
  }

  static class Chips {

    private final String name;

    public Chips(String path) {
      this.name = path;
    }

    public String getName() {
      return name;
    }
  }
}
