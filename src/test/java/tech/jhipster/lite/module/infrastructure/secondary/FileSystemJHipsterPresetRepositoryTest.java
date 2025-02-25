package tech.jhipster.lite.module.infrastructure.secondary;

import static java.nio.charset.StandardCharsets.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.JsonHelper;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterModuleSlug;
import tech.jhipster.lite.module.domain.JHipsterModuleSlugs;
import tech.jhipster.lite.module.domain.ProjectFiles;
import tech.jhipster.lite.module.domain.preset.Preset;
import tech.jhipster.lite.module.domain.preset.PresetName;
import tech.jhipster.lite.module.domain.preset.Presets;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

@UnitTest
class FileSystemJHipsterPresetRepositoryTest {

  private static final String DEFAULT_PRESET_FOLDER = "presets";

  private static final String PRESET_JSON_TEMPLATE =
    """
    {
      "presets": [
        {
          "name": "%s",
          "modules": [
            %s
          ]
        }
      ]
    }
    """;
  private static final String PRESET_JSON_MULTIPLE_TEMPLATE =
    """
    {
      "presets": [
        %s
      ]
    }
    """;
  private static final String PRESET_ENTRY_TEMPLATE =
    """
    {
      "name": "%s",
      "modules": [
        %s
      ]
    }
    """;

  @Test
  void shouldHandleDeserializationErrors() throws IOException {
    ObjectMapper json = mock(ObjectMapper.class);
    when(json.readValue(any(byte[].class), eq(PersistedPresets.class))).thenThrow(IOException.class);
    FileSystemJHipsterPresetRepository fileSystemJHipsterPresetRepository = new FileSystemJHipsterPresetRepository(
      json,
      mockProjectFilesWithValidPresetJson(),
      DEFAULT_PRESET_FOLDER
    );

    assertThatThrownBy(fileSystemJHipsterPresetRepository::getPresets).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldNotReturnPresetFromUnknownFolder() {
    ProjectFiles projectFiles = mock(ProjectFiles.class);
    lenient().when(projectFiles.findPaths("/%s".formatted(DEFAULT_PRESET_FOLDER))).thenThrow(GeneratorException.class);
    FileSystemJHipsterPresetRepository fileSystemJHipsterPresetRepository = new FileSystemJHipsterPresetRepository(
      JsonHelper.jsonMapper(),
      projectFiles,
      DEFAULT_PRESET_FOLDER
    );

    assertThatThrownBy(fileSystemJHipsterPresetRepository::getPresets).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldNotReturnPresetFromUnknownFile() {
    ProjectFiles projectFiles = mockProjectFilesWithValidPresetJson();
    lenient().when(projectFiles.readBytes("/presets/preset-maven.json")).thenThrow(GeneratorException.class);
    FileSystemJHipsterPresetRepository fileSystemJHipsterPresetRepository = new FileSystemJHipsterPresetRepository(
      JsonHelper.jsonMapper(),
      projectFiles,
      DEFAULT_PRESET_FOLDER
    );

    assertThatThrownBy(fileSystemJHipsterPresetRepository::getPresets).isExactlyInstanceOf(GeneratorException.class);
  }

  @Test
  void shouldGetExistingPresetInAlphabeticalOrder() {
    FileSystemJHipsterPresetRepository fileSystemJHipsterPresetRepository = new FileSystemJHipsterPresetRepository(
      JsonHelper.jsonMapper(),
      mockProjectFilesWithValidPresetJson(),
      DEFAULT_PRESET_FOLDER
    );

    Presets presets = fileSystemJHipsterPresetRepository.getPresets();

    assertThat(presets.presets()).containsExactly(
      expectedPresetAngularCore(),
      expectedPresetJavaLibraryWithGradle(),
      expectedPresetJavaLibraryWithMaven(),
      expectedPresetVueCore(),
      expectedPresetWebappVueSpringBoot()
    );
  }

  private static ProjectFiles mockProjectFilesWithValidPresetJson() {
    ProjectFiles projectFiles = mock(ProjectFiles.class);

    String mavenPreset = createPresetJson(
      "Java Library with Maven",
      """
      "init",
      "application-service-hexagonal-architecture-documentation",
      "maven-java",
      "maven-wrapper",
      "prettier",
      "java-base",
      "checkstyle",
      "jacoco-with-min-coverage-check"
      """
    );

    String gradlePreset = createPresetJson(
      "Java Library with Gradle",
      """
      "init",
      "application-service-hexagonal-architecture-documentation",
      "gradle-java",
      "gradle-wrapper",
      "prettier",
      "java-base",
      "checkstyle",
      "jacoco-with-min-coverage-check"
      """
    );

    String typescriptPreset = createPresetJson(
      "Vue Core",
      """
      "init",
      "application-service-hexagonal-architecture-documentation",
      "prettier",
      "typescript",
      "vue-core"
      """
    );

    String personalPresets = createMultiplePresetsJson(
      createPresetEntry(
        "Webapp: Vue + Spring Boot",
        """
        "init",
        "application-service-hexagonal-architecture-documentation",
        "maven-java",
        "maven-wrapper",
        "prettier",
        "vue-core",
        "java-base",
        "checkstyle",
        "jacoco-with-min-coverage-check",
        "spring-boot",
        "spring-boot-mvc-empty",
        "logs-spy",
        "spring-boot-tomcat",
        "frontend-maven-plugin"
        """
      ),
      createPresetEntry(
        "Angular Core",
        """
        "init",
        "application-service-hexagonal-architecture-documentation",
        "prettier",
        "typescript",
        "angular-core"
        """
      )
    );

    lenient().when(projectFiles.readBytes("/presets/preset-maven.json")).thenReturn(mavenPreset.getBytes(UTF_8));
    lenient().when(projectFiles.readBytes("/presets/preset-gradle.json")).thenReturn(gradlePreset.getBytes(UTF_8));
    lenient().when(projectFiles.readBytes("/presets/preset-typescript.json")).thenReturn(typescriptPreset.getBytes(UTF_8));
    lenient().when(projectFiles.readBytes("/presets/personal/preset-personal.json")).thenReturn(personalPresets.getBytes(UTF_8));

    lenient()
      .when(projectFiles.findPaths("/presets"))
      .thenReturn(
        List.of(
          "/presets/preset-maven.json",
          "/presets/preset-gradle.json",
          "/presets/preset-typescript.json",
          "/presets/personal/preset-personal.json"
        )
      );

    return projectFiles;
  }

  private static String createPresetJson(String name, String modules) {
    return PRESET_JSON_TEMPLATE.formatted(name, modules);
  }

  private static String createPresetEntry(String name, String modules) {
    return PRESET_ENTRY_TEMPLATE.formatted(name, modules);
  }

  private static String createMultiplePresetsJson(String... presetEntries) {
    return PRESET_JSON_MULTIPLE_TEMPLATE.formatted(String.join(",\n", presetEntries));
  }

  private static Preset expectedPresetJavaLibraryWithMaven() {
    return new Preset(
      new PresetName("Java Library with Maven"),
      new JHipsterModuleSlugs(
        List.of(
          new JHipsterModuleSlug("init"),
          new JHipsterModuleSlug("application-service-hexagonal-architecture-documentation"),
          new JHipsterModuleSlug("maven-java"),
          new JHipsterModuleSlug("maven-wrapper"),
          new JHipsterModuleSlug("prettier"),
          new JHipsterModuleSlug("java-base"),
          new JHipsterModuleSlug("checkstyle"),
          new JHipsterModuleSlug("jacoco-with-min-coverage-check")
        )
      )
    );
  }

  private static Preset expectedPresetJavaLibraryWithGradle() {
    return new Preset(
      new PresetName("Java Library with Gradle"),
      new JHipsterModuleSlugs(
        List.of(
          new JHipsterModuleSlug("init"),
          new JHipsterModuleSlug("application-service-hexagonal-architecture-documentation"),
          new JHipsterModuleSlug("gradle-java"),
          new JHipsterModuleSlug("gradle-wrapper"),
          new JHipsterModuleSlug("prettier"),
          new JHipsterModuleSlug("java-base"),
          new JHipsterModuleSlug("checkstyle"),
          new JHipsterModuleSlug("jacoco-with-min-coverage-check")
        )
      )
    );
  }

  private static Preset expectedPresetVueCore() {
    return new Preset(
      new PresetName("Vue Core"),
      new JHipsterModuleSlugs(
        List.of(
          new JHipsterModuleSlug("init"),
          new JHipsterModuleSlug("application-service-hexagonal-architecture-documentation"),
          new JHipsterModuleSlug("prettier"),
          new JHipsterModuleSlug("typescript"),
          new JHipsterModuleSlug("vue-core")
        )
      )
    );
  }

  private static Preset expectedPresetWebappVueSpringBoot() {
    return new Preset(
      new PresetName("Webapp: Vue + Spring Boot"),
      new JHipsterModuleSlugs(
        List.of(
          new JHipsterModuleSlug("init"),
          new JHipsterModuleSlug("application-service-hexagonal-architecture-documentation"),
          new JHipsterModuleSlug("maven-java"),
          new JHipsterModuleSlug("maven-wrapper"),
          new JHipsterModuleSlug("prettier"),
          new JHipsterModuleSlug("vue-core"),
          new JHipsterModuleSlug("java-base"),
          new JHipsterModuleSlug("checkstyle"),
          new JHipsterModuleSlug("jacoco-with-min-coverage-check"),
          new JHipsterModuleSlug("spring-boot"),
          new JHipsterModuleSlug("spring-boot-mvc-empty"),
          new JHipsterModuleSlug("logs-spy"),
          new JHipsterModuleSlug("spring-boot-tomcat"),
          new JHipsterModuleSlug("frontend-maven-plugin")
        )
      )
    );
  }

  private static Preset expectedPresetAngularCore() {
    return new Preset(
      new PresetName("Angular Core"),
      new JHipsterModuleSlugs(
        List.of(
          new JHipsterModuleSlug("init"),
          new JHipsterModuleSlug("application-service-hexagonal-architecture-documentation"),
          new JHipsterModuleSlug("prettier"),
          new JHipsterModuleSlug("typescript"),
          new JHipsterModuleSlug("angular-core")
        )
      )
    );
  }
}
