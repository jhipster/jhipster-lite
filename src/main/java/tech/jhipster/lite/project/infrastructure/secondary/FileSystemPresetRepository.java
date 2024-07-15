package tech.jhipster.lite.project.infrastructure.secondary;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import tech.jhipster.lite.project.domain.preset.Preset;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

class FileSystemPresetRepository {

  public Collection<Preset> get(Path path) {
    if (Files.notExists(path)) {
      return List.of();
    }

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      return objectMapper.readValue(Files.readAllBytes(path), PersistedPresets.class).toDomain();
    } catch (IOException e) {
      throw GeneratorException.technicalError("Can't read presets: " + e.getMessage(), e);
    }
  }
}
