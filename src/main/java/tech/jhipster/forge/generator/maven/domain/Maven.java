package tech.jhipster.forge.generator.maven.domain;

import static tech.jhipster.forge.common.utils.FileUtils.*;
import static tech.jhipster.forge.common.utils.WordUtils.indent;

import java.io.IOException;
import java.nio.file.Files;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.error.domain.GeneratorException;

public class Maven {

  public static final String POM_XML = "pom.xml";

  private Maven() {}

  public static boolean isMavenProject(Project project) {
    return FileUtils.exists(getPath(project.getPath(), POM_XML));
  }

  public static void addParent(Project project, Parent parent) {
    try {
      int indent = Integer.parseInt(project.getConfig("prettierDefaultIndent").orElse("2"));
      String locationPomXml = getPath(project.getPath(), POM_XML);
      String currentPomXml = read(locationPomXml);
      String updatedPomXml = FileUtils.replace(currentPomXml, Parent.NEEDLE, parent.get(indent));
      Files.write(getPathOf(locationPomXml), updatedPomXml.getBytes());
    } catch (IOException e) {
      throw new GeneratorException("Error when adding parent");
    }
  }

  public static void addDependency(Project project, Dependency dependency) {
    try {
      int indent = Integer.parseInt(project.getConfig("prettierDefaultIndent").orElse("2"));
      String locationPomXml = getPath(project.getPath(), POM_XML);
      String currentPomXml = read(locationPomXml);
      String dependencyWithNeedle = dependency.get(indent) + System.lineSeparator() + indent(2, indent) + Dependency.NEEDLE;
      String updatedPomXml = FileUtils.replace(currentPomXml, Dependency.NEEDLE, dependencyWithNeedle);
      Files.write(getPathOf(locationPomXml), updatedPomXml.getBytes());
    } catch (IOException e) {
      throw new GeneratorException("Error when adding dependency");
    }
  }

  public static void addPlugin(Project project, Plugin plugin) {
    try {
      int indent = Integer.parseInt(project.getConfig("prettierDefaultIndent").orElse("2"));
      String locationPomXml = getPath(project.getPath(), POM_XML);
      String currentPomXml = read(locationPomXml);
      String pluginWithNeedle = plugin.get(indent) + System.lineSeparator() + indent(3, indent) + Plugin.NEEDLE;
      String updatedPomXml = FileUtils.replace(currentPomXml, Plugin.NEEDLE, pluginWithNeedle);
      Files.write(getPathOf(locationPomXml), updatedPomXml.getBytes());
    } catch (IOException e) {
      throw new GeneratorException("Error when adding dependency");
    }
  }
}
