package tech.jhipster.forge.common.domain;

import java.io.File;

public class Constants {

  private Constants() {}

  public static final String MAIN_RESOURCES = String.join(File.separator, "src", "main", "resources");
  public static final String TEST_RESOURCES = String.join(File.separator, "src", "test", "resources");
  public static final String TEMPLATE_RESOURCES = String.join(File.separator, MAIN_RESOURCES, "template");
  public static final String TEST_TEMPLATE_RESOURCES = String.join(File.separator, TEST_RESOURCES, "template");
}
