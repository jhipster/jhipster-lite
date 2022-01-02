package tech.jhipster.lite.generator.project.infrastructure.secondary.executables;

import static tech.jhipster.lite.common.domain.OSUtils.isWindows;

public class Npm {

  private Npm() {}

  public static String getExecutableCommand() {
    if (isWindows()) {
      return "npm.cmd";
    } else {
      return "npm";
    }
  }
}
