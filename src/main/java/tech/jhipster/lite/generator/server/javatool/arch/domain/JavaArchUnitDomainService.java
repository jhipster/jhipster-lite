package tech.jhipster.lite.generator.server.javatool.arch.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_PATH;

import java.util.Arrays;
import java.util.stream.Collectors;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;

public class JavaArchUnitDomainService implements JavaArchUnitService {

  public static final String SOURCE = "server/javatool/arch";
  public static final String TECH_INFRA_PRIMARY_PATH = "technical/infrastructure/primary";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;

  public JavaArchUnitDomainService(ProjectRepository projectRepository, BuildToolService buildToolService) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
  }

  @Override
  public void init(Project project) {
    addHexagobalArchJavaFiles(project);
    addArchUnitMavenPlugin(project);
  }

  @Override
  public void addHexagobalArchJavaFiles(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);

    String packageNamePath = project.getPackageNamePath().orElse(PACKAGE_PATH);
    String packageWalkPath = Arrays.stream(packageNamePath.split("/")).map(s -> "\"" + s + "\"").collect(Collectors.joining(", "));
    project.addConfig("packageWalkPath", packageWalkPath);

    projectRepository.template(project, SOURCE, "BusinessContext.java", getPath(MAIN_JAVA, packageNamePath));
    projectRepository.template(project, SOURCE, "SharedKernel.java", getPath(MAIN_JAVA, packageNamePath));
    projectRepository.template(project, SOURCE, "HexagonalArchTest.java", getPath(TEST_JAVA, packageNamePath, TECH_INFRA_PRIMARY_PATH));
  }

  @Override
  public void addArchUnitMavenPlugin(Project project) {
    buildToolService
      .getVersion(project, "archunit-junit5")
      .ifPresentOrElse(
        version -> {
          buildToolService.addProperty(project, ArchUnit.getPropertyName(), version);
          buildToolService.addDependency(project, ArchUnit.archUnitDependency());
        },
        () -> {
          throw new GeneratorException("Version not found: archunit-junit5");
        }
      );
  }
}
