package tech.jhipster.lite.module.infrastructure.primary;

import tech.jhipster.lite.error.domain.GeneratorException;

class InvalidModuleQueryException extends GeneratorException {

  private InvalidModuleQueryException(String message) {
    super(message);
  }

  static InvalidModuleQueryException unknownModule(String slug) {
    return new InvalidModuleQueryException("Module %s does not exist".formatted(slug));
  }
}
