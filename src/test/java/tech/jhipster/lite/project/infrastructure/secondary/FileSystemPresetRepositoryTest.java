package tech.jhipster.lite.project.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.project.domain.preset.Preset;

@UnitTest
class FileSystemPresetRepositoryTest {

  @Test
  void shouldGetExistingPreset() {
    FileSystemPresetRepository presetRepository = new FileSystemPresetRepository();

    Collection<Preset> presets = presetRepository.get("src/test/resources/projects/preset/preset.json");

    Preset expectedPreset = new Preset(
      "angular + spring boot",
      List.of(
        "init",
        "application-service-hexagonal-architecture-documentation",
        "maven-java",
        "prettier",
        "angular-core",
        "java-base",
        "maven-wrapper",
        "spring-boot",
        "spring-boot-mvc-empty",
        "logs-spy",
        "spring-boot-tomcat"
      )
    );
    assertThat(presets).containsExactly(expectedPreset);
  }
}
