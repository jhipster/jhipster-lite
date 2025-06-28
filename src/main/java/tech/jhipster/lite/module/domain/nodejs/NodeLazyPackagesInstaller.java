package tech.jhipster.lite.module.domain.nodejs;

import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

/**
 * Install Node.js packages if a previous installation has already been done.
 */
public interface NodeLazyPackagesInstaller {
  void runInstallIn(JHipsterProjectFolder folder, NodePackageManager nodePackageManager);
}
