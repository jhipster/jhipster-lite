package tech.jhipster.forge.generator.maven.domain;

import static tech.jhipster.forge.common.utils.FileUtils.getPath;
import static tech.jhipster.forge.common.utils.FileUtils.read;
import static tech.jhipster.forge.common.utils.WordUtils.indent;
import static tech.jhipster.forge.generator.maven.domain.Maven.*;

import java.io.IOException;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.domain.ProjectRepository;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.common.utils.WordUtils;
import tech.jhipster.forge.error.domain.GeneratorException;
import tech.jhipster.forge.generator.shared.domain.Dependency;
import tech.jhipster.forge.generator.shared.domain.Parent;
import tech.jhipster.forge.generator.shared.domain.Plugin;

public class MavenDomainService implements MavenService {

  public static final String SOURCE = "maven";
  public static final String POM_XML = "pom.xml";

  private final ProjectRepository projectRepository;

  public MavenDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void initPomXml(Project project) {
    project.addDefaultConfig("packageName");
    project.addDefaultConfig("projectName");
    project.addDefaultConfig("baseName");

    String baseName = project.getConfig("baseName").orElse("");
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

  @Override
  public void addParent(Project project, Parent parent) {
    try {
      int indent = Integer.parseInt(project.getConfig("prettierDefaultIndent").orElse("2"));
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
      int indent = Integer.parseInt(project.getConfig("prettierDefaultIndent").orElse("2"));
      String locationPomXml = getPath(project.getPath(), POM_XML);
      String currentPomXml = read(locationPomXml);
      String dependencyWithNeedle =
        Maven.getDependency(dependency, indent) + System.lineSeparator() + indent(2, indent) + NEEDLE_DEPENDENCY;
      String updatedPomXml = FileUtils.replace(currentPomXml, NEEDLE_DEPENDENCY, dependencyWithNeedle);

      projectRepository.write(project, updatedPomXml, ".", "pom.xml");
    } catch (IOException e) {
      throw new GeneratorException("Error when adding dependency");
    }
  }

  @Override
  public void addPlugin(Project project, Plugin plugin) {
    try {
      int indent = Integer.parseInt(project.getConfig("prettierDefaultIndent").orElse("2"));
      String locationPomXml = getPath(project.getPath(), POM_XML);
      String currentPomXml = read(locationPomXml);
      String pluginWithNeedle = Maven.getPlugin(plugin, indent) + System.lineSeparator() + indent(3, indent) + NEEDLE_PLUGIN;
      String updatedPomXml = FileUtils.replace(currentPomXml, NEEDLE_PLUGIN, pluginWithNeedle);

      projectRepository.write(project, updatedPomXml, ".", "pom.xml");
    } catch (IOException e) {
      throw new GeneratorException("Error when adding dependency");
    }
  }
}
