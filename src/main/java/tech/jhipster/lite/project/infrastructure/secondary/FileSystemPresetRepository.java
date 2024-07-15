package tech.jhipster.lite.project.infrastructure.secondary;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import tech.jhipster.lite.project.domain.preset.Preset;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

class FileSystemPresetRepository {

  public static final String PRESET_FILE = "preset.json";

  public Collection<Preset> get(Path path) {
    Assert.notNull("path", path);

    Path presetFilePath = presetFilePath(path);

    if (Files.notExists(presetFilePath)) {
      return List.of();
    }

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      return objectMapper.readValue(Files.readAllBytes(presetFilePath), PersistedPresets.class).toDomain();
    } catch (IOException e) {
      throw GeneratorException.technicalError("Can't read presets: " + e.getMessage(), e);
    }
  }

  private static Path presetFilePath(Path path) {
    return path.resolve(PRESET_FILE);
  }
}
