package tech.jhipster.lite.generator.module.domain;

import java.nio.file.Path;
import tech.jhipster.lite.error.domain.Assert;

public class JHipsterSource {

  private final Path source;

  JHipsterSource(Path source) {
    Assert.notNull("source", source);

    this.source = source;
  }

  public Path get() {
    return source;
  }

  public JHipsterSource forFile(String file) {
    return new JHipsterSource(source.resolve(file));
  }
}
