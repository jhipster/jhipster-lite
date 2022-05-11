package tech.jhipster.lite.generator.buildtool.gradle.domain;

import tech.jhipster.lite.common.domain.WordUtils;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectFile;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

import java.util.List;

import static tech.jhipster.lite.common.domain.FileUtils.REGEXP_SPACE_STAR;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.buildtool.gradle.domain.Gradle.GRADLE_NEEDLE_DEPENDENCY;
import static tech.jhipster.lite.generator.project.domain.Constants.BUILD_GRADLE_KTS;
import static tech.jhipster.lite.generator.project.domain.Constants.SETTINGS_GRADLE_KTS;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;

public class GradleDomainService implements GradleService {

  public static final String SOURCE = "buildtool/gradle";

  private final ProjectRepository projectRepository;

  public GradleDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void initJava(Project project) {
    addJavaBuildGradleKts(project);
    addGradleWrapper(project);
  }

  @Override
  public void addJavaBuildGradleKts(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    project.addDefaultConfig(PROJECT_NAME);
    project.addDefaultConfig(BASE_NAME);

    String baseName = project.getBaseName().orElse("");
    project.addConfig("dasherizedBaseName", WordUtils.kebabCase(baseName));

    projectRepository.template(project, SOURCE, BUILD_GRADLE_KTS);
    projectRepository.template(project, SOURCE, SETTINGS_GRADLE_KTS);
  }

  @Override
  public void addGradleWrapper(Project project) {
    List<ProjectFile> files = Gradle
      .gradleWrapper()
      .entrySet()
      .stream()
      .map(entry ->
        ProjectFile
          .forProject(project)
          .withSource(getPath(SOURCE, entry.getValue()), entry.getKey())
          .withDestinationFolder(entry.getValue())
      )
      .toList();

    projectRepository.add(files);
    projectRepository.setExecutable(project, "", "gradlew");
    projectRepository.setExecutable(project, "", "gradlew.bat");
  }

  @Override
  public void addDependency(Project project, Dependency dependency) {
    String newDependency = Gradle.getDependency(dependency);
    projectRepository.replaceText(project, "", BUILD_GRADLE_KTS, REGEXP_SPACE_STAR + GRADLE_NEEDLE_DEPENDENCY, newDependency);
  }

}
