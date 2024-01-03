package tech.jhipster.lite.module.infrastructure.secondary.javadependency;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import tech.jhipster.lite.module.domain.ProjectFiles;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.module.infrastructure.secondary.javadependency.gradle.VersionsCatalog;
import tech.jhipster.lite.shared.error.domain.GeneratorException;

@Repository
@Order(Ordered.LOWEST_PRECEDENCE)
class GradleVersionCatalogDependenciesReader implements JavaDependenciesReader {

  private static final String CURRENT_VERSIONS_FILE = "/generator/dependencies/gradle/libs.versions.toml";
  private final VersionsCatalog versionsCatalog;

  public GradleVersionCatalogDependenciesReader(ProjectFiles files) {
    String tomlConfigContent = files.readString(CURRENT_VERSIONS_FILE);
    try {
      Path tempFile = Files.createTempFile(null, ".toml");
      Files.writeString(tempFile, tomlConfigContent);
      versionsCatalog = new VersionsCatalog(tempFile);
    } catch (IOException exception) {
      throw GeneratorException.technicalError(
        "Error creating temporary file for %s content: %s".formatted(CURRENT_VERSIONS_FILE, exception.getMessage()),
        exception
      );
    }
  }

  @Override
  public JavaDependenciesVersions get() {
    return new JavaDependenciesVersions(versionsCatalog.retrieveVersions());
  }
}
