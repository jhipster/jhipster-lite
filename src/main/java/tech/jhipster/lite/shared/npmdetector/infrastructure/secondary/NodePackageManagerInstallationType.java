package tech.jhipster.lite.shared.npmdetector.infrastructure.secondary;

import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

@ExcludeFromGeneratedCodeCoverage(reason = "Cases can only be tested by using different computers")
public enum NodePackageManagerInstallationType {
  NONE,
  UNIX,
  WINDOWS,
}
