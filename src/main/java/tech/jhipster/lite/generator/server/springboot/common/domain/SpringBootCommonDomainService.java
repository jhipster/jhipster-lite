package tech.jhipster.lite.generator.server.springboot.common.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.WordUtils.LF;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.*;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.NEEDLE_APPLICATION_TEST_PROPERTIES;

import java.util.Optional;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class SpringBootCommonDomainService implements SpringBootCommonService {

  public static final String SOURCE = "server/springboot/common";

  private final ProjectRepository projectRepository;

  public SpringBootCommonDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addTestLogbackRecorder(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    project.addDefaultConfig(BASE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    projectRepository.template(project, getPath(SOURCE, "test"), "LogbackRecorder.java", getPath(TEST_JAVA, packageNamePath));
  }

  @Override
  public void addProperties(Project project, String key, Object value) {
    addKeyValueToProperties(project, key, value, MAIN_RESOURCES, APPLICATION_PROPERTIES, NEEDLE_APPLICATION_PROPERTIES);
  }

  @Override
  public void addPropertiesFast(Project project, String key, Object value) {
    addKeyValueToProperties(project, key, value, MAIN_RESOURCES, APPLICATION_FAST_PROPERTIES, NEEDLE_APPLICATION_FAST_PROPERTIES);
  }

  @Override
  public void addPropertiesTest(Project project, String key, Object value) {
    addKeyValueToProperties(project, key, value, TEST_RESOURCES, APPLICATION_PROPERTIES, NEEDLE_APPLICATION_TEST_PROPERTIES);
  }

  private void addKeyValueToProperties(
    Project project,
    String key,
    Object value,
    String folderProperties,
    String fileProperties,
    String needleProperties
  ) {
    String propertiesWithNeedle = key + "=" + value + LF + needleProperties;
    projectRepository.replaceText(
      project,
      getPath(folderProperties, CONFIG_FOLDER),
      fileProperties,
      needleProperties,
      propertiesWithNeedle
    );
  }

  @Override
  public void addPropertiesNewLine(Project project) {
    addNewLineToProperties(project, MAIN_RESOURCES, APPLICATION_PROPERTIES, NEEDLE_APPLICATION_PROPERTIES);
  }

  @Override
  public void addPropertiesFastNewLine(Project project) {
    addNewLineToProperties(project, MAIN_RESOURCES, APPLICATION_FAST_PROPERTIES, NEEDLE_APPLICATION_FAST_PROPERTIES);
  }

  @Override
  public void addPropertiesTestNewLine(Project project) {
    addNewLineToProperties(project, TEST_RESOURCES, APPLICATION_PROPERTIES, NEEDLE_APPLICATION_TEST_PROPERTIES);
  }

  private void addNewLineToProperties(Project project, String folderProperties, String fileProperties, String needleProperties) {
    String propertiesWithNeedle = LF + needleProperties;
    projectRepository.replaceText(
      project,
      getPath(folderProperties, CONFIG_FOLDER),
      fileProperties,
      needleProperties,
      propertiesWithNeedle
    );
  }

  @Override
  public void addPropertiesComment(Project project, String text) {
    addCommentToProperties(project, text, MAIN_RESOURCES, APPLICATION_PROPERTIES, NEEDLE_APPLICATION_PROPERTIES);
  }

  @Override
  public void addPropertiesFastComment(Project project, String text) {
    addCommentToProperties(project, text, MAIN_RESOURCES, APPLICATION_FAST_PROPERTIES, NEEDLE_APPLICATION_FAST_PROPERTIES);
  }

  @Override
  public void addPropertiesTestComment(Project project, String text) {
    addCommentToProperties(project, text, TEST_RESOURCES, APPLICATION_PROPERTIES, NEEDLE_APPLICATION_TEST_PROPERTIES);
  }

  private void addCommentToProperties(
    Project project,
    String text,
    String folderProperties,
    String fileProperties,
    String needleProperties
  ) {
    String propertiesWithNeedle = "# " + text + LF + needleProperties;
    projectRepository.replaceText(
      project,
      getPath(folderProperties, CONFIG_FOLDER),
      fileProperties,
      needleProperties,
      propertiesWithNeedle
    );
  }

  @Override
  public void addLogger(Project project, String packageName, Level level) {
    addLoggerToConfiguration(project, packageName, level, MAIN_RESOURCES, LOGGING_CONFIGURATION, NEEDLE_LOGBACK_LOGGER);
  }

  @Override
  public void addLoggerTest(Project project, String packageName, Level level) {
    addLoggerToConfiguration(project, packageName, level, TEST_RESOURCES, LOGGING_TEST_CONFIGURATION, NEEDLE_LOGBACK_LOGGER);
  }

  @Override
  public Optional<String> getProperty(Project project, String key) {
    Assert.notNull("project", project);
    Assert.notBlank("key", key);

    return FileUtils
      .readLine(getPath(project.getFolder(), MAIN_RESOURCES, CONFIG_FOLDER, "application.properties"), key + "=")
      .map(readValue -> {
        String[] result = readValue.split("=");
        if (result.length == 2) {
          return result[1];
        }
        return null;
      });
  }

  private void addLoggerToConfiguration(
    Project project,
    String packageName,
    Level level,
    String folderConfig,
    String fileLoggingConfig,
    String needleLogger
  ) {
    String loggerWithNeedle =
      String.format("<logger name=\"%s\" level=\"%s\" />", packageName, level.toString()) + LF + "  " + needleLogger;
    projectRepository.replaceText(project, getPath(folderConfig), fileLoggingConfig, needleLogger, loggerWithNeedle);
  }
}
