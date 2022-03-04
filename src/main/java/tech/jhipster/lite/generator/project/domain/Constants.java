package tech.jhipster.lite.generator.project.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;

public class Constants {

  private Constants() {}

  public static final String MAIN_JAVA = getPath("src/main/java");
  public static final String MAIN_WEBAPP = getPath("src/main/webapp");
  public static final String MAIN_RESOURCES = getPath("src/main/resources");

  public static final String TEMPLATE_FOLDER = "generator";
  public static final String DEPENDENCIES_FOLDER = "dependencies";
  public static final String CONFIG_FOLDER = "config";

  public static final String TEST_JAVA = getPath("src/test/java");
  public static final String TEST_RESOURCES = getPath("src/test/resources");
  public static final String TEST_TEMPLATE_RESOURCES = getPath(TEST_RESOURCES, TEMPLATE_FOLDER);

  public static final String POM_XML = "pom.xml";
  public static final String LIQUIBASE_MASTER_XML = "master.xml";
  public static final String PACKAGE_JSON = "package.json";
  public static final String DOCKERFILE = "Dockerfile";

  public static final String COMMENT_PROPERTIES_PREFIX = "#";
  public static final String KEY_VALUE_PROPERTIES_SEPARATOR = "=";

  // Hexagonal Architecture
  public static final String TECHNICAL = "technical";
  public static final String INFRASTRUCTURE = getPath(TECHNICAL, "infrastructure");
  public static final String INFRA_PRIMARY = getPath(INFRASTRUCTURE, "primary");
  public static final String INFRA_SECONDARY = getPath(INFRASTRUCTURE, "secondary");
}
