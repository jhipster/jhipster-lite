package tech.jhipster.lite.generator.module.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;

public class JHipsterFileContent {

  private final Path source;

  JHipsterFileContent(Path source) {
    Assert.notNull("source", source);

    this.source = source;
  }

  public String read(JHipsterModuleContext context) {
    Assert.notNull("context", context);

    try {
      String rawContent = Files.readString(source.toAbsolutePath());
      return ArgumentsReplacer.replaceArguments(rawContent, context.get());
    } catch (IOException e) {
      throw new GeneratorException("Error reading file: " + source + ": " + e.getMessage(), e);
    }
  }
}
