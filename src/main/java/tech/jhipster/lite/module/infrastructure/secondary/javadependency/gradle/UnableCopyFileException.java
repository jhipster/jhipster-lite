package tech.jhipster.lite.module.infrastructure.secondary.javadependency.gradle;

import tech.jhipster.lite.shared.error.domain.GeneratorException;

class UnableCopyFileException extends GeneratorException {

  UnableCopyFileException(String source, String destination, Throwable cause) {
    super(
      internalServerError(GradleDependencyErrorKey.UNABLE_COPY_FILE)
        .message("Unable to copy file: %s to %s".formatted(source, destination))
        .addParameter("sourceFile", source)
        .addParameter("destinationFile", destination)
        .cause(cause)
    );
  }
}
