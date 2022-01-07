package tech.jhipster.lite.generator.packagemanager.npm.domain;

import static tech.jhipster.lite.common.domain.OSUtils.isWindows;

public class Npm {

  private Npm() {}

  public static String getExecutableCommand() {
    return isWindows() ? "npm.cmd" : "npm";
  }
}
