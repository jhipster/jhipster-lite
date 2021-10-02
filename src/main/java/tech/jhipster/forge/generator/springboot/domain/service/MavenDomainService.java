package tech.jhipster.forge.generator.springboot.domain.service;

import static tech.jhipster.forge.common.domain.DefaultConfig.*;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;
import static tech.jhipster.forge.common.utils.FileUtils.read;
import static tech.jhipster.forge.common.utils.WordUtils.indent;
import static tech.jhipster.forge.generator.springboot.domain.service.Maven.*;

import java.io.IOException;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.common.utils.WordUtils;
import tech.jhipster.forge.error.domain.GeneratorException;
import tech.jhipster.forge.generator.springboot.domain.model.Dependency;
import tech.jhipster.forge.generator.springboot.domain.model.Parent;
import tech.jhipster.forge.generator.springboot.domain.model.Plugin;
import tech.jhipster.forge.generator.springboot.domain.usecase.MavenService;

public class MavenDomainService implements MavenService {

  public static final String SOURCE = "maven";
  public static final String POM_XML = "pom.xml";

  private final ProjectRepository projectRepository;

  public MavenDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void addParent(Project project, Parent parent) {
    try {
      project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);
      int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(2);
      String locationPomXml = getPath(project.getPath(), POM_XML);
      String currentPomXml = read(locationPomXml);
      String updatedPomXml = FileUtils.replace(currentPomXml, NEEDLE_PARENT, Maven.getParent(parent, indent));

      projectRepository.write(project, updatedPomXml, ".", "pom.xml");
    } catch (IOException e) {
      throw new GeneratorException("Error when adding parent");
    }
  }

  @Override
  public void addDependency(Project project, Dependency dependency) {
    try {
      project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);
      int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(2);
      String locationPomXml = getPath(project.getPath(), POM_XML);
      String currentPomXml = read(locationPomXml);
      String needle = dependency.getScope().orElse("").equals("test") ? NEEDLE_DEPENDENCY_TEST : NEEDLE_DEPENDENCY;
      String dependencyWithNeedle = Maven.getDependency(dependency, indent) + System.lineSeparator() + indent(2, indent) + needle;
      String updatedPomXml = FileUtils.replace(currentPomXml, needle, dependencyWithNeedle);

      projectRepository.write(project, updatedPomXml, ".", "pom.xml");
    } catch (IOException e) {
      throw new GeneratorException("Error when adding dependency");
    }
  }

  @Override
  public void addPlugin(Project project, Plugin plugin) {
    try {
      project.addDefaultConfig(PRETTIER_DEFAULT_INDENT);
      int indent = (Integer) project.getConfig(PRETTIER_DEFAULT_INDENT).orElse(2);
      String locationPomXml = getPath(project.getPath(), POM_XML);
      String currentPomXml = read(locationPomXml);
      String pluginWithNeedle = Maven.getPlugin(plugin, indent) + System.lineSeparator() + indent(3, indent) + NEEDLE_PLUGIN;
      String updatedPomXml = FileUtils.replace(currentPomXml, NEEDLE_PLUGIN, pluginWithNeedle);

      projectRepository.write(project, updatedPomXml, ".", "pom.xml");
    } catch (IOException e) {
      throw new GeneratorException("Error when adding dependency");
    }
  }

  @Override
  public void init(Project project) {
    addPomXml(project);
    addMavenWrapper(project);
  }

  @Override
  public void addPomXml(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    project.addDefaultConfig(PROJECT_NAME);
    project.addDefaultConfig(BASE_NAME);

    String baseName = project.getBaseName().orElse("");
    project.addConfig("dasherizedBaseName", WordUtils.kebabCase(baseName));

    projectRepository.template(project, SOURCE, "pom.xml");
  }

  @Override
  public void addMavenWrapper(Project project) {
    projectRepository.add(project, SOURCE, "mvnw");
    projectRepository.add(project, SOURCE, "mvnw.cmd");

    String sourceWrapper = getPath(SOURCE, ".mvn", "wrapper");
    String destinationWrapper = getPath(".mvn", "wrapper");

    projectRepository.add(project, sourceWrapper, "MavenWrapperDownloader.java", destinationWrapper);
    projectRepository.add(project, sourceWrapper, "maven-wrapper.jar", destinationWrapper);
    projectRepository.add(project, sourceWrapper, "maven-wrapper.properties", destinationWrapper);
  }
}
