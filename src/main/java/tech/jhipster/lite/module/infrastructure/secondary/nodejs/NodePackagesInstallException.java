package tech.jhipster.lite.module.infrastructure.secondary.nodejs;

import tech.jhipster.lite.shared.error.domain.GeneratorException;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

@ExcludeFromGeneratedCodeCoverage
class NodePackagesInstallException extends GeneratorException {

  public NodePackagesInstallException(String message) {
    super(internalServerError(NodeErrorKey.INSTALL_ERROR).message(message));
  }

  public NodePackagesInstallException(String message, Throwable cause) {
    super(internalServerError(NodeErrorKey.INSTALL_ERROR).message(message).cause(cause));
  }
}
