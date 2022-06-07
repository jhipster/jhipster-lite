package tech.jhipster.lite.generator.module.infrastructure.primary;

import java.io.IOException;
import tech.jhipster.lite.error.domain.GeneratorException;

class InvalidProjectQueryException extends GeneratorException {

  private InvalidProjectQueryException(String message) {
    super(message);
  }

  private InvalidProjectQueryException(String message, Throwable cause) {
    super(message, cause);
  }

  static InvalidProjectQueryException unsupportedMethod() {
    return new InvalidProjectQueryException("Only POST supported for JHipster module with a project DTO");
  }

  static InvalidProjectQueryException unreadablePayload(IOException cause) {
    return new InvalidProjectQueryException("Can't read payload to apply module: " + cause.getMessage(), cause);
  }
}
