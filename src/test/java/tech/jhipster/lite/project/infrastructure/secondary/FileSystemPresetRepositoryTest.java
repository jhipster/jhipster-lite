package tech.jhipster.lite.project.infrastructure.secondary;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.ProjectFiles;
import tech.jhipster.lite.module.infrastructure.secondary.FileSystemProjectFiles;
import tech.jhipster.lite.project.domain.ModuleSlug;
import tech.jhipster.lite.project.domain.preset.Preset;
import tech.jhipster.lite.project.domain.preset.PresetName;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

@UnitTest
@ExtendWith(MockitoExtension.class)
class FileSystemPresetRepositoryTest {

  @Mock
  private ProjectFiles projectFiles;

  private FileSystemPresetRepository presetRepository;

  @Test
  void shouldNotReturnPresetFromUnknownFile() {
    presetRepository = new FileSystemPresetRepository(new FileSystemProjectFiles());
    assertThatThrownBy(() -> presetRepository.get()).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldGetExistingPreset() {
    presetRepository = new FileSystemPresetRepository(projectFiles);
    String jsonContent =
      """
      {
        "presets": [
          {
            "name": "angular + spring boot",
            "modules": [
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
            ]
          }
        ]
      }
      """;
    lenient().when(projectFiles.readBytes("preset.json")).thenReturn(jsonContent.getBytes());

    Collection<Preset> presets = presetRepository.get();

    Preset expectedPreset = new Preset(
      new PresetName("angular + spring boot"),
      List.of(
        new ModuleSlug("init"),
        new ModuleSlug("application-service-hexagonal-architecture-documentation"),
        new ModuleSlug("maven-java"),
        new ModuleSlug("prettier"),
        new ModuleSlug("angular-core"),
        new ModuleSlug("java-base"),
        new ModuleSlug("maven-wrapper"),
        new ModuleSlug("spring-boot"),
        new ModuleSlug("spring-boot-mvc-empty"),
        new ModuleSlug("logs-spy"),
        new ModuleSlug("spring-boot-tomcat")
      )
    );
    assertThat(presets).containsExactly(expectedPreset);
  }
}
