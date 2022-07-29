package tech.jhipster.lite.module.domain;

import java.util.Collection;
import java.util.stream.Stream;
import tech.jhipster.lite.common.domain.JHipsterCollections;

public record FilesToDelete(Collection<JHipsterProjectFilePath> files) {
  public FilesToDelete(Collection<JHipsterProjectFilePath> files) {
    this.files = JHipsterCollections.immutable(files);
  }

  public Stream<JHipsterProjectFilePath> stream() {
    return files().stream();
  }
}
