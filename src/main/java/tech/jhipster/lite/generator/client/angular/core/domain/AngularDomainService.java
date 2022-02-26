package tech.jhipster.lite.generator.client.angular.core.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_WEBAPP;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;

import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class AngularDomainService implements AngularService {

  public static final String SOURCE = "client/angular";
  public static final String SOURCE_WEBAPP = "client/angular/src/main/webapp";
  private static final String APP = "src/main/webapp/app/common/primary/app";
  public static final String SOURCE_PRIMARY = getPath(SOURCE, APP);
  public static final String DESTINATION_PRIMARY = APP;

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public AngularDomainService(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Override
  public void addAngular(Project project) {
    addCommonAngular(project);
    addAppFilesWithoutCss(project);
  }

  @Override
  public void addStyledAngular(Project project) {
    addCommonAngular(project);
    addAppFilesWithCss(project);
  }

  private void addCommonAngular(Project project) {
    addDependencies(project);
    addDevDependencies(project);
    addScripts(project);
    addJestSonar(project);
    addFiles(project);
    addAngularFiles(project);
  }

  public void addAppFilesWithoutCss(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE_PRIMARY, "app.component.html", DESTINATION_PRIMARY);
    projectRepository.template(project, SOURCE_PRIMARY, "app.component.ts", DESTINATION_PRIMARY);
  }

  public void addAppFilesWithCss(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE_PRIMARY, "styledApp.component.html", DESTINATION_PRIMARY, "app.component.html");
    projectRepository.template(project, SOURCE_PRIMARY, "styledApp.component.ts", DESTINATION_PRIMARY, "app.component.ts");

    projectRepository.template(
      project,
      getPath(SOURCE_WEBAPP, "app/common/primary/app"),
      "app.component.css",
      getPath(MAIN_WEBAPP, "app/common/primary/app")
    );
    addImages(project);
  }

  public void addDependencies(Project project) {
    Angular.dependencies().forEach(dependency -> addDependency(project, dependency));
  }

  public void addDevDependencies(Project project) {
    Angular.devDependencies().forEach(devDependency -> addDevDependency(project, devDependency));
  }

  private void addDependency(Project project, String dependency) {
    npmService
      .getVersion("angular", dependency)
      .ifPresentOrElse(
        version -> npmService.addDependency(project, dependency, version),
        () -> {
          throw new GeneratorException("Dependency not found: " + dependency);
        }
      );
  }

  private void addDevDependency(Project project, String devDependency) {
    npmService
      .getVersion("angular", devDependency)
      .ifPresentOrElse(
        version -> npmService.addDevDependency(project, devDependency, version),
        () -> {
          throw new GeneratorException("DevDependency not found: " + devDependency);
        }
      );
  }

  public void addScripts(Project project) {
    Angular.scripts().forEach((name, cmd) -> npmService.addScript(project, name, cmd));
  }

  public void addFiles(Project project) {
    Angular.files().forEach(file -> projectRepository.add(project, SOURCE, file));
  }

  public void addAngularFiles(Project project) {
    project.addDefaultConfig(BASE_NAME);
    Angular
      .angularFiles()
      .forEach((file, path) -> projectRepository.template(project, getPath(SOURCE_WEBAPP, path), file, getPath(MAIN_WEBAPP, path)));
  }

  public void addImages(Project project) {
    projectRepository.add(
      project,
      getPath(SOURCE_WEBAPP, "content/images"),
      "JHipster-Lite-neon-red.png",
      "src/main/webapp/content/images"
    );
    projectRepository.add(project, getPath(SOURCE_WEBAPP, "content/images"), "AngularLogo.svg", "src/main/webapp/content/images");
  }

  public void addJestSonar(Project project) {
    String oldText = "\"cacheDirectories\": \\[";
    String newText =
      """
      "jestSonar": \\{
          "reportPath": "target/test-results/jest",
          "reportFile": "TESTS-results-sonar.xml"
        \\},
        "cacheDirectories": \\[""";
    projectRepository.replaceText(project, "", PACKAGE_JSON, oldText, newText);
  }
}
