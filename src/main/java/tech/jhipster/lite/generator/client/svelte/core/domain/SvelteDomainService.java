package tech.jhipster.lite.generator.client.svelte.core.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;

import java.util.List;
import java.util.Map;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class SvelteDomainService implements SvelteService {

  public static final String SOURCE = "client/svelte";

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public SvelteDomainService(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Override
  public void addSvelte(Project project) {
    addCommonSvelteKit(project);
    addUnstyledHomeFiles(project);
  }

  @Override
  public void addStyledSvelteKit(Project project) {
    addCommonSvelteKit(project);
    addStyledHomeFiles(project);
  }

  public void addCommonSvelteKit(Project project) {
    addDependencies(project);
    addDevDependencies(project);
    addScripts(project);
    addJestSonar(project);
    addType(project);
    addSvelteConfigFile(project);
    addRootFiles(project);
    addAppFiles(project);
  }

  public void addDependencies(Project project) {
    Svelte.dependencies().forEach(dependency -> addDependency(project, dependency));
  }

  public void addDevDependencies(Project project) {
    Svelte.devDependencies().forEach(devDependency -> addDevDependency(project, devDependency));
  }

  private void addDependency(Project project, String dependency) {
    npmService
      .getVersion("svelte", dependency)
      .ifPresentOrElse(
        version -> npmService.addDependency(project, dependency, version),
        () -> {
          throw new GeneratorException("Dependency not found: " + dependency);
        }
      );
  }

  private void addDevDependency(Project project, String devDependency) {
    npmService
      .getVersion("svelte", devDependency)
      .ifPresentOrElse(
        version -> npmService.addDevDependency(project, devDependency, version),
        () -> {
          throw new GeneratorException("DevDependency not found: " + devDependency);
        }
      );
  }

  public void addScripts(Project project) {
    // prettier-ignore
    Map
      .of(
        "dev", "svelte-kit dev",
        "build", "svelte-kit build",
        "package", "svelte-kit package",
        "preview", "svelte-kit preview",
        "check", "svelte-check --tsconfig ./tsconfig.json",
        "check,watch", "svelte-check --tsconfig ./tsconfig.json --watch",
        "lint", "prettier --ignore-path .gitignore --check --plugin-search-dir=. . && eslint --ignore-path .gitignore .",
        "format", "prettier --ignore-path .gitignore --write --plugin-search-dir=. .",
        "test", "jest"
      )
      .forEach((name, cmd) -> npmService.addScript(project, name, cmd));
  }

  public void addSvelteConfigFile(Project project) {
    List
      .of(".eslintrc.cjs", "tsconfig.json", "svelte.config.js", ".prettierrc", "jest.config.cjs", "babel.config.cjs")
      .forEach(file -> projectRepository.add(project, SOURCE, file));
  }

  public void addRootFiles(Project project) {
    String pathWebapp = "src/main/webapp";

    projectRepository.template(project, getPath(SOURCE, pathWebapp), "app.html", pathWebapp);
    projectRepository.template(project, getPath(SOURCE, pathWebapp), "app.d.ts", pathWebapp);
    projectRepository.template(project, getPath(SOURCE, pathWebapp), "jest-setup.ts", pathWebapp);
  }

  public void addAppFiles(Project project) {
    String sourcePrimary = getPath(SOURCE, "src/main/webapp/app/common/primary/app");
    String sourceRoutes = getPath(SOURCE, "src/main/webapp/routes");
    String destinationPrimary = "src/main/webapp/app/common/primary/app";
    String destinationRoutes = "src/main/webapp/routes";
    String sourceAssets = "src/main/webapp/assets";
    String destinationAssets = "src/main/webapp/assets";

    projectRepository.template(project, sourcePrimary, "App.svelte", destinationPrimary);
    projectRepository.template(project, sourceRoutes, "index.svelte", destinationRoutes);

    projectRepository.template(
      project,
      getPath(SOURCE, "src/tests/spec/common/primary/app"),
      "App.spec.ts",
      "src/tests/javascript/spec/common/primary/app"
    );

    projectRepository.add(project, getPath(SOURCE, sourceAssets), "JHipster-Lite-neon-orange.png", destinationAssets);
    projectRepository.add(project, getPath(SOURCE, sourceAssets), "svelte-logo.png", destinationAssets);
  }

  public void addUnstyledHomeFiles(Project project) {
    String sourcePrimary = getPath(SOURCE, "src/main/webapp/app/common/primary/app");
    String destinationPrimary = "src/main/webapp/app/common/primary/app";

    projectRepository.template(project, sourcePrimary, "App.svelte", destinationPrimary);
  }

  public void addStyledHomeFiles(Project project) {
    String sourcePrimary = getPath(SOURCE, "src/main/webapp/app/common/primary/app");
    String destinationPrimary = "src/main/webapp/app/common/primary/app";
    String sourceAssets = "src/main/webapp/assets";
    String destinationAssets = "src/main/webapp/assets";

    projectRepository.template(project, sourcePrimary, "StyledApp.svelte", destinationPrimary, "App.svelte");

    projectRepository.add(project, getPath(SOURCE, sourceAssets), "JHipster-Lite-neon-orange.png", destinationAssets);
    projectRepository.add(project, getPath(SOURCE, sourceAssets), "svelte-logo.png", destinationAssets);
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

  public void addType(Project project) {
    String oldText = "\"cacheDirectories\": \\[";
    String newText = """
      "type": "module",
        "cacheDirectories": \\[""";
    projectRepository.replaceText(project, "", PACKAGE_JSON, oldText, newText);
  }
}
