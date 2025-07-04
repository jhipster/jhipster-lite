package tech.jhipster.lite.module.infrastructure.primary;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.JsonHelper;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModuleSlug;
import tech.jhipster.lite.module.domain.JHipsterModuleSlugs;
import tech.jhipster.lite.module.domain.preset.Preset;
import tech.jhipster.lite.module.domain.preset.PresetName;
import tech.jhipster.lite.module.domain.preset.Presets;

@UnitTest
class RestPresetsTest {

  @Test
  void shouldSerializeToJson() {
    assertThat(JsonHelper.writeAsString(RestPresets.from(presets()))).isEqualTo(json());
  }

  private static Presets presets() {
    return new Presets(
      List.of(
        new Preset(
          new PresetName("test preset one"),
          new JHipsterModuleSlugs(List.of(new JHipsterModuleSlug("test-module-one"), new JHipsterModuleSlug("test-module-two")))
        ),
        new Preset(
          new PresetName("test preset two"),
          new JHipsterModuleSlugs(List.of(new JHipsterModuleSlug("test-module-three"), new JHipsterModuleSlug("test-module-four")))
        )
      )
    );
  }

  private static String json() {
    return """
    {\
    "presets":[\
    {\
    "name":"test preset one",\
    "modules":[\
    {"slug":"test-module-one"},\
    {"slug":"test-module-two"}\
    ]\
    },\
    {\
    "name":"test preset two",\
    "modules":[\
    {"slug":"test-module-three"},\
    {"slug":"test-module-four"}\
    ]\
    }\
    ]\
    }\
    """;
  }
}
