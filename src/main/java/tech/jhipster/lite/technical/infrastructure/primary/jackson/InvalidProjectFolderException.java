package tech.jhipster.lite.technical.infrastructure.primary.jackson;

import tech.jhipster.lite.error.domain.GeneratorException;

public class InvalidProjectFolderException extends GeneratorException {

  public InvalidProjectFolderException(String fixedFolderPath) {
    super("Should start with " + fixedFolderPath);
  }
}
