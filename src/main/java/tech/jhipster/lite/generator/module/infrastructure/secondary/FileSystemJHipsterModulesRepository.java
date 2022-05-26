package tech.jhipster.lite.generator.module.infrastructure.secondary;

import java.io.IOException;
import java.nio.file.Files;
import java.util.function.Consumer;
import org.springframework.stereotype.Repository;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.module.domain.JHipsterModule;
import tech.jhipster.lite.generator.module.domain.JHipsterModuleFile;
import tech.jhipster.lite.generator.module.domain.JHipsterModulesRepository;

@Repository
class FileSystemJHipsterModulesRepository implements JHipsterModulesRepository {

  @Override
  public void apply(JHipsterModule module) {
    Assert.notNull("module", module);

    writeFiles(module);
  }

  private void writeFiles(JHipsterModule module) {
    module.files().forEach(writeFile(module));
  }

  private Consumer<JHipsterModuleFile> writeFile(JHipsterModule module) {
    return file -> {
      try {
        Files.createDirectories(file.destination().folder(module.projectFolder()));
        Files.writeString(file.destination().pathInProject(module.projectFolder()), file.content().read(module.context()));
      } catch (IOException e) {
        throw new GeneratorException("Can't write file to " + file.destination().toString() + ": " + e.getMessage(), e);
      }
    };
  }
}
