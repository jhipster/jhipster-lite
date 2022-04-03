package tech.jhipster.lite.generator.client.vue.core.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.common.domain.WordUtils.LF;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_TYPESCRIPT;
import static tech.jhipster.lite.generator.project.domain.Constants.PACKAGE_JSON;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;

import java.util.List;
import java.util.Map;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.packagemanager.npm.domain.NpmService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class VueDomainService implements VueService {

  public static final String SOURCE = "client/vue";
  public static final String NEEDLE_IMPORT = "// jhipster-needle-main-ts-import";
  public static final String NEEDLE_PROVIDER = "// jhipster-needle-main-ts-provider";
  public static final String SOURCE_PRIMARY = getPath(SOURCE, "webapp/app/common/primary/app");
  public static final String DESTINATION_PRIMARY = "src/main/webapp/app/common/primary/app";
  public static final String DESTINATION_APP = "src/main/webapp/app";

  private final ProjectRepository projectRepository;
  private final NpmService npmService;

  public VueDomainService(ProjectRepository projectRepository, NpmService npmService) {
    this.projectRepository = projectRepository;
    this.npmService = npmService;
  }

  @Override
  public void addVue(Project project) {
    addCommonVue(project);
    addAppFilesWithoutCss(project);
  }

  @Override
  public void addStyledVue(Project project) {
    addCommonVue(project);
    addAppFilesWithCss(project);
  }

  private void addCommonVue(Project project) {
    Assert.notNull("project", project);
    addDependencies(project);
    addDevDependencies(project);
    addScripts(project);
    addJestSonar(project);
    addViteConfigFiles(project);
    addRootFiles(project);
    addAppFiles(project);
    addRouter(project);
  }

  public void addDependencies(Project project) {
    Vue.dependencies().forEach(dependency -> addDependency(project, dependency));
  }

  public void addDevDependencies(Project project) {
    Vue.devDependencies().forEach(devDependency -> addDevDependency(project, devDependency));
  }

  public void addRouter(Project project) {
    addRouterDependency(project);
    addRouterFiles(project);
  }

  private void addRouterDependency(Project project) {
    Vue.routerDependencies().forEach(dependency -> addDependency(project, dependency));
  }

  private void addDependency(Project project, String dependency) {
    npmService
      .getVersion("vue", dependency)
      .ifPresentOrElse(
        version -> npmService.addDependency(project, dependency, version),
        () -> {
          throw new GeneratorException("Dependency not found: " + dependency);
        }
      );
  }

  private void addDevDependency(Project project, String devDependency) {
    npmService
      .getVersion("vue", devDependency)
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
        "build", "vue-tsc --noEmit && vite build --emptyOutDir",
        "dev", "vite",
        "jest", "jest src/test/javascript/spec --logHeapUsage --maxWorkers=2 --no-cache",
        "preview", "vite preview",
        "start", "vite",
        "test", "npm run jest --",
        "test:watch", "npm run jest -- --watch"
      )
      .forEach((name, cmd) -> npmService.addScript(project, name, cmd));
  }

  public void addViteConfigFiles(Project project) {
    List
      .of(".eslintrc.js", "jest.config.js", "tsconfig.json", "vite.config.ts")
      .forEach(file -> projectRepository.add(project, SOURCE, file));
  }

  public void addRootFiles(Project project) {
    projectRepository.template(project, getPath(SOURCE, "webapp"), "index.html", "src/main/webapp");
    projectRepository.template(project, getPath(SOURCE, "webapp/app"), "env.d.ts", DESTINATION_APP);
    projectRepository.template(project, getPath(SOURCE, "webapp/app"), MAIN_TYPESCRIPT, DESTINATION_APP);
  }

  private void addRouterFiles(Project project) {
    addRouterConfigAndTestFiles(project);
    addRouterMainConfiguration(project);
  }

  private void addRouterConfigAndTestFiles(Project project) {
    projectRepository.template(project, getPath(SOURCE, "webapp/app/router"), "router.ts", "src/main/webapp/app/router");
    projectRepository.template(project, getPath(SOURCE, "test/spec/router"), "Router.spec.ts", "src/test/javascript/spec/router");
  }

  private void addRouterMainConfiguration(Project project) {
    addNewNeedleLineToFile(project, Vue.ROUTER_IMPORT, DESTINATION_APP, MAIN_TYPESCRIPT, NEEDLE_IMPORT);
    addNewNeedleLineToFile(project, Vue.ROUTER_PROVIDER, DESTINATION_APP, MAIN_TYPESCRIPT, NEEDLE_PROVIDER);
  }

  private void addNewNeedleLineToFile(Project project, String importLine, String folder, String file, String needle) {
    String importWithNeedle = importLine + LF + needle;
    projectRepository.replaceText(project, getPath(folder), file, needle, importWithNeedle);
  }

  public void addAppFiles(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE_PRIMARY, "App.component.ts", DESTINATION_PRIMARY);
    projectRepository.template(project, SOURCE_PRIMARY, "index.ts", DESTINATION_PRIMARY);

    projectRepository.template(
      project,
      getPath(SOURCE, "test/spec/common/primary/app"),
      "App.spec.ts",
      "src/test/javascript/spec/common/primary/app"
    );
  }

  public void addAppFilesWithoutCss(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE_PRIMARY, "App.html", DESTINATION_PRIMARY);
    projectRepository.template(project, SOURCE_PRIMARY, "App.vue", DESTINATION_PRIMARY);
  }

  public void addAppFilesWithCss(Project project) {
    project.addDefaultConfig(BASE_NAME);

    projectRepository.template(project, SOURCE_PRIMARY, "StyledApp.html", DESTINATION_PRIMARY, "App.html");
    projectRepository.template(project, SOURCE_PRIMARY, "StyledApp.vue", DESTINATION_PRIMARY, "App.vue");

    projectRepository.add(
      project,
      getPath(SOURCE, "webapp/content/images"),
      "JHipster-Lite-neon-green.png",
      "src/main/webapp/content/images"
    );
    projectRepository.add(project, getPath(SOURCE, "webapp/content/images"), "VueLogo.png", "src/main/webapp/content/images");
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
