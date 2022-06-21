package tech.jhipster.lite.generator.module.infrastructure.secondary;

import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.module.domain.properties.JHipsterProjectFolder;

class MissingPackageJsonException extends GeneratorException {

  public MissingPackageJsonException(JHipsterProjectFolder folder) {
    super("package.json is missing in " + folder.get() + ", can't apply module");
  }
}
