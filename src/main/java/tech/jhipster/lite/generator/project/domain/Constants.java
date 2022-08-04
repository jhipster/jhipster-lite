package tech.jhipster.lite.generator.project.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;

public class Constants {

  private Constants() {}

  public static final String TEMPLATE_FOLDER = "generator";
  public static final String DEPENDENCIES_FOLDER = "dependencies";

  public static final String TEST_TEMPLATE_RESOURCES = getPath("src/test/resources", TEMPLATE_FOLDER);

  public static final String PACKAGE_JSON = "package.json";
  public static final String README_MD = "README.md";
}
