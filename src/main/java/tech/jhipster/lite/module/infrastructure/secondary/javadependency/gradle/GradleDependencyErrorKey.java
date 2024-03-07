package tech.jhipster.lite.module.infrastructure.secondary.javadependency.gradle;

import tech.jhipster.lite.shared.error.domain.ErrorKey;

enum GradleDependencyErrorKey implements ErrorKey {
  INVALID_TOML_VERSION_CATALOG("invalid-toml-version-catalog-file"),
  UNABLE_CREATE_FOLDER("unable-create-folder"),
  UNABLE_COPY_FILE("unable-copy-file");

  private final String key;

  GradleDependencyErrorKey(String key) {
    this.key = key;
  }

  @Override
  public String get() {
    return key;
  }
}
