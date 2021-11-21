package tech.jhipster.light.generator.server.springboot.properties.domain;

import static tech.jhipster.light.common.domain.FileUtils.getPath;
import static tech.jhipster.light.common.domain.FileUtils.read;
import static tech.jhipster.light.generator.project.domain.Constants.MAIN_RESOURCES;
import static tech.jhipster.light.generator.project.domain.Constants.TEST_RESOURCES;
import static tech.jhipster.light.generator.server.springboot.core.domain.SpringBoot.*;

import java.io.IOException;
import tech.jhipster.light.common.domain.FileUtils;
import tech.jhipster.light.error.domain.GeneratorException;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.project.domain.ProjectRepository;

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
    try {
      String currentApplicationProperties = read(getPath(project.getFolder(), folderProperties, "config", fileProperties));
      String propertiesWithNeedle = key + "=" + value + System.lineSeparator() + needleProperties;
      String updatedApplicationProperties = FileUtils.replace(currentApplicationProperties, needleProperties, propertiesWithNeedle);

      projectRepository.write(project, updatedApplicationProperties, getPath(folderProperties, "config"), fileProperties);
    } catch (IOException e) {
      throw new GeneratorException("Error when adding properties");
    }
  }
}
