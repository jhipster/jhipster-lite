package tech.jhipster.lite.generator.server.springboot.logging.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.*;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class SpringBootLoggingDomainService implements SpringBootLoggingService {

  private final ProjectRepository projectRepository;

  public SpringBootLoggingDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addLogger(Project project, String packageName, String level) {
    addLoggerToConfiguration(project, packageName, level, MAIN_RESOURCES, LOGGING_CONFIGURATION, NEEDLE_LOGBACK_LOGGER);
  }

  @Override
  public void addLoggerTest(Project project, String packageName, String level) {
    addLoggerToConfiguration(project, packageName, level, TEST_RESOURCES, LOGGING_TEST_CONFIGURATION, NEEDLE_LOGBACK_LOGGER);
  }

  private void addLoggerToConfiguration(
    Project project,
    String packageName,
    String level,
    String folderConfig,
    String fileLoggingConfig,
    String needleLogger
  ) {
    String loggerWithNeedle =
      String.format("<logger name=\"%s\" level=\"%s\"/>", packageName, level) + System.lineSeparator() + needleLogger;
    projectRepository.replaceText(project, getPath(folderConfig), fileLoggingConfig, needleLogger, loggerWithNeedle);
  }
}
