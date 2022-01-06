package tech.jhipster.lite.generator.server.springboot.properties.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.lite.generator.server.springboot.core.domain.SpringBoot.*;

import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class SpringBootPropertiesDomainService implements SpringBootPropertiesService {

  private final ProjectRepository projectRepository;

  public SpringBootPropertiesDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
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
    String propertiesWithNeedle = key + "=" + value + project.getEndOfLine() + needleProperties;
    projectRepository.replaceText(project, getPath(folderProperties, "config"), fileProperties, needleProperties, propertiesWithNeedle);
  }
}
