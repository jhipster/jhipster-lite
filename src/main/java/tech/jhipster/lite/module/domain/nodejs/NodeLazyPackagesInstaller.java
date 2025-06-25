package tech.jhipster.lite.module.domain.nodejs;

import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

/**
 * Run Node.js install if a previous Node.js install has already been done.
 */
public interface NodeLazyPackagesInstaller {
  void runInstallIn(JHipsterProjectFolder folder);
}
