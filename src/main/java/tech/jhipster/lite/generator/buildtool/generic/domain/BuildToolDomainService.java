package tech.jhipster.lite.generator.buildtool.generic.domain;

import java.util.Optional;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.gradle.domain.GradleService;
import tech.jhipster.lite.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.lite.generator.project.domain.Project;

public class BuildToolDomainService implements BuildToolService {

  public static final String EXCEPTION_NO_BUILD_TOOL = "No build tool";
  private final MavenService mavenService;
  private final GradleService gradleService;

  public BuildToolDomainService(MavenService mavenService, GradleService gradleService) {
    this.mavenService = mavenService;
    this.gradleService = gradleService;
  }

  @Override
  public Optional<String> getVersion(Project project, String name) {
    if (project.isMavenProject()) {
      return mavenService.getVersion(name);
    }
    throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
  }

  @Override
  public Optional<String> getGroup(Project project) {
    if (project.isMavenProject()) {
      return mavenService.getGroupId(project.getFolder());
    } else if (project.isGradleProject()) {
      return gradleService.getGroup(project.getFolder());
    }
    throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
  }

  @Override
  public Optional<String> getName(Project project) {
    if (project.isMavenProject()) {
      return mavenService.getName(project.getFolder());
    }
    throw new GeneratorException(EXCEPTION_NO_BUILD_TOOL);
  }
}
