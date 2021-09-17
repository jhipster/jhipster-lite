package tech.jhipster.forge.generator.maven.domain;

import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.utils.FileUtils;

public class Maven {

  private Maven() {}

  public static boolean isMavenProject(Project project) {
    return FileUtils.exists(getPath(project.getPath(), "pom.xml"));
  }
}
