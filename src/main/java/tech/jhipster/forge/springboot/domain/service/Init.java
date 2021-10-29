package tech.jhipster.forge.springboot.domain.service;

public class Init {

  public static final String NODE_VERSION = "14.18.1";

  private Init() {}

  public static String getNodeVersion() {
    return NODE_VERSION;
  }
}
