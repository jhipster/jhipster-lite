package tech.jhipster.lite.module.infrastructure.secondary;

import java.nio.file.Path;
import java.util.function.Consumer;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.javaproperties.*;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

@Service
class FileSystemSpringFactoriesCommandsHandler {

  public void handle(JHipsterProjectFolder projectFolder, SpringFactories factories) {
    Assert.notNull("projectFolder", projectFolder);
    Assert.notNull("factories", factories);

    factories.get().forEach(setProperty(projectFolder));
  }

  private Consumer<SpringFactory> setProperty(JHipsterProjectFolder projectFolder) {
    return property -> new PropertiesFileSpringFactoriesHandler(getPath(projectFolder, property)).set(property.key(), property.value());
  }

  private static Path getPath(JHipsterProjectFolder projectFolder, SpringFactory factory) {
    return switch (factory.type()) {
      // todo probably move it in the repository class
      case TEST_FACTORIES -> projectFolder.filePath("src/test/resources/META-INF/" + "spring.factories");
    };
  }
}
