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
      String locationPomXml = getPath(project.getPath(), POM_XML);
      String currentPomXml = read(locationPomXml);
      String updatedPomXml = FileUtils.replace(currentPomXml, Parent.NEEDLE, parent.get());
      Files.write(getPathOf(locationPomXml), updatedPomXml.getBytes());
    } catch (IOException e) {
      throw new GeneratorException("Error when adding parent");
    }
  }

  public static void addDependency(Project project, Dependency dependency) {
    try {
      String locationPomXml = getPath(project.getPath(), POM_XML);
      String currentPomXml = read(locationPomXml);
      String dependencyWithNeedle = dependency.get() + System.lineSeparator() + indent(2, 2) + Dependency.NEEDLE;
      String updatedPomXml = FileUtils.replace(currentPomXml, Dependency.NEEDLE, dependencyWithNeedle);
      Files.write(getPathOf(locationPomXml), updatedPomXml.getBytes());
    } catch (IOException e) {
      throw new GeneratorException("Error when adding dependency");
    }
  }
}
