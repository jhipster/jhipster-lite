package tech.jhipster.lite.generator.project.infrastructure.secondary.executables;

import static tech.jhipster.lite.common.domain.OSUtils.isWindows;

public class Npm {

  private Npm() {}

  public static String getExecutableCommand() {
    return isWindows() ? "npm.cmd" : "npm";
  }
}
