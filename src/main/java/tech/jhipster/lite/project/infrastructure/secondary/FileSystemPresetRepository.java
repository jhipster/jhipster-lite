package tech.jhipster.lite.project.infrastructure.secondary;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collection;
import tech.jhipster.lite.module.domain.ProjectFiles;
import tech.jhipster.lite.project.domain.preset.Preset;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

class FileSystemPresetRepository {

  public static final String PRESET_FILE = "preset.json";

  private final ProjectFiles projectFiles;
  private final ObjectMapper json;

  public FileSystemPresetRepository(ProjectFiles projectFiles, ObjectMapper json) {
    Assert.notNull("projectFiles", projectFiles);
    Assert.notNull("json", json);

    this.projectFiles = projectFiles;
    this.json = json;
  }

  public Collection<Preset> get() {
    try {
      return json.readValue(projectFiles.readBytes(PRESET_FILE), PersistedPresets.class).toDomain();
    } catch (IOException e) {
      throw GeneratorException.technicalError("Can't read presets: " + e.getMessage(), e);
    }
  }
}
