package tech.jhipster.lite.module.infrastructure.secondary.nodejs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.jhipster.lite.module.domain.nodejs.NodeLazyPackagesInstaller;
import tech.jhipster.lite.module.domain.nodejs.NodePackageManager;
import tech.jhipster.lite.module.domain.properties.JHipsterProjectFolder;

public class NoopNodeLazyPackagesInstaller implements NodeLazyPackagesInstaller {

  private static final Logger log = LoggerFactory.getLogger(NoopNodeLazyPackagesInstaller.class);

  @Override
  public void runInstallIn(JHipsterProjectFolder folder, NodePackageManager nodePackageManager) {
    log.info("Simulating installation of Node.js dependencies");
  }
}
