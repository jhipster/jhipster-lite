package tech.jhipster.lite.module.infrastructure.secondary;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import tech.jhipster.lite.module.domain.startupcommand.DockerComposeFile;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.error.domain.GeneratorException;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public class DockerComposeFileHandler {

  private static final String DASH = "-";
  private static final String LINE_BREAK = System.lineSeparator();
  private static final String SPACER = " ";

  private final Path file;

  public DockerComposeFileHandler(Path file) {
    Assert.notNull("file", file);

    this.file = file;
  }

  public void append(DockerComposeFile dockerComposeFile) {
    Assert.notNull("dockerComposeFile", dockerComposeFile);

    updateDockerComposeFile(dockerComposeFile);
  }

  @ExcludeFromGeneratedCodeCoverage(reason = "Hard to cover IOException")
  private void updateDockerComposeFile(DockerComposeFile dockerComposeFile) {
    try {
      String properties = buildDockerComposeFile(dockerComposeFile);

      Files.writeString(file, properties);
    } catch (IOException e) {
      throw GeneratorException.technicalError("Error updating root compose file: " + e.getMessage(), e);
    }
  }

  private String buildDockerComposeFile(DockerComposeFile dockerComposeFile) throws IOException {
    String currentProperties = readOrInitDockerComposeFile();

    int propertyIndex = currentProperties.indexOf(dockerComposeLine(dockerComposeFile));
    if (propertyIndex != -1) {
      return currentProperties;
    }
    return addNewDockerComposeFile(dockerComposeFile, currentProperties);
  }

  private String addNewDockerComposeFile(DockerComposeFile compose, String currentRootCompose) {
    return currentRootCompose + dockerComposeLine(compose) + LINE_BREAK;
  }

  private String dockerComposeLine(DockerComposeFile compose) {
    return SPACER.repeat(2) + DASH + SPACER + compose.path();
  }

  private String readOrInitDockerComposeFile() throws IOException {
    if (Files.notExists(file)) {
      Files.createDirectories(file.getParent());
      Files.createFile(file);

      return "include:" + LINE_BREAK;
    }

    return Files.readString(file);
  }
}
