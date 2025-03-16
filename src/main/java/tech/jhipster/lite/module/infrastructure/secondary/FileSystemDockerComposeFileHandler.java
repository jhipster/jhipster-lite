package tech.jhipster.lite.module.infrastructure.secondary;

import java.nio.file.Path;
import java.util.function.Consumer;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;
import tech.jhipster.lite.module.domain.standalonedocker.JHipsterModuleDockerComposeFile;
import tech.jhipster.lite.module.domain.startupcommand.DockerComposeFile;
import tech.jhipster.lite.shared.error.domain.Assert;

class FileSystemDockerComposeFileHandler {

  public static final String COMPOSE_FILE_NAME = "docker-compose.yml";

  public void handle(JHipsterProjectFolder projectFolder, JHipsterModuleDockerComposeFile files) {
    Assert.notNull("projectFolder", projectFolder);
    Assert.notNull("files", files);

    files.dockerComposeFiles().get().forEach(include(projectFolder));
  }

  private Consumer<DockerComposeFile> include(JHipsterProjectFolder projectFolder) {
    return file -> new DockerComposeFileHandler(getPath(projectFolder)).append(file);
  }

  private static Path getPath(JHipsterProjectFolder projectFolder) {
    return projectFolder.filePath(COMPOSE_FILE_NAME);
  }
}
