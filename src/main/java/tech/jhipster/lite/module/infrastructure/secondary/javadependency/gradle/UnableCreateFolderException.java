package tech.jhipster.lite.module.infrastructure.secondary.javadependency.gradle;

import tech.jhipster.lite.shared.error.domain.GeneratorException;

class UnableCreateFolderException extends GeneratorException {

  public UnableCreateFolderException(String folderPath, Throwable cause) {
    super(
      internalServerError(GradleDependencyErrorKey.UNABLE_CREATE_FOLDER)
        .message("Unable to create folder(s): %s".formatted(folderPath))
        .addParameter("folderPath", folderPath)
        .cause(cause)
    );
  }
}
