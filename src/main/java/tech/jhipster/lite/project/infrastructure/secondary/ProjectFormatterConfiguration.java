package tech.jhipster.lite.project.infrastructure.secondary;

import static tech.jhipster.lite.module.domain.nodejs.NodePackageManager.NPM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import tech.jhipster.lite.shared.npmdetector.infrastructure.secondary.NodePackageManagerInstallationReader;

@Configuration
@ImportRuntimeHints(NativeHints.class)
class ProjectFormatterConfiguration {

  private static final Logger log = LoggerFactory.getLogger(ProjectFormatterConfiguration.class);

  @Bean
  public ProjectFormatter projectFormatter(NodePackageManagerInstallationReader packageManagerInstallationReader) {
    return switch (packageManagerInstallationReader.get(NPM)) {
      case UNIX -> unixFormatter();
      case WINDOWS -> windowsFormatter();
      case NONE -> traceFormatter();
    };
  }

  private NpmProjectFormatter unixFormatter() {
    log.info("Using unix formatter");

    return new NpmProjectFormatter("npm");
  }

  private NpmProjectFormatter windowsFormatter() {
    log.info("Using windows formatter");

    return new NpmProjectFormatter("npm.cmd");
  }

  private TraceProjectFormatter traceFormatter() {
    log.info("Using trace formatter");

    return new TraceProjectFormatter();
  }
}
