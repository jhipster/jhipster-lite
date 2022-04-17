package tech.jhipster.lite.generator.server.springboot.webflux.web.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommon.TECHNICAL_INFRASTRUCTURE_PRIMARY_EXCEPTION;
import static tech.jhipster.lite.generator.server.springboot.webflux.web.domain.SpringBootWebflux.*;
import static tech.jhipster.lite.generator.server.springboot.webflux.web.domain.SpringBootWebflux.problemSpringWebfluxDependency;
import static tech.jhipster.lite.generator.server.springboot.webflux.web.domain.SpringBootWebflux.springBootStarterWebfluxDependency;

import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.DefaultConfig;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

public class SpringBootWebfluxDomainService implements SpringBootWebfluxService {

  public static final String SOURCE = "server/springboot/webflux/web";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;

  public SpringBootWebfluxDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
  }

  @Override
  public void init(Project project) {
    addSpringBootWebflux(project);
    addExceptionHandler(project);
  }

  @Override
  public void addSpringBootWebflux(Project project) {
    buildToolService.addDependency(project, springBootStarterWebfluxDependency());
    addProperties(project);
  }

  @Override
  public void addExceptionHandler(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);

    this.buildToolService.getVersion(project, "problem-spring")
      .ifPresentOrElse(
        version -> {
          buildToolService.addProperty(project, "problem-spring.version", version);
          buildToolService.addProperty(project, "problem-spring-webflux.version", "\\${problem-spring.version}");
        },
        () -> {
          throw new GeneratorException("Problem Spring version not found");
        }
      );

    buildToolService.addDependency(project, springBootStarterValidation());
    buildToolService.addDependency(project, problemSpringWebfluxDependency());

    addExceptionHandlerProperties(project);

    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    templateToExceptionHandler(project, packageNamePath, "src", "ProblemConfiguration.java", MAIN_JAVA);
    templateToExceptionHandler(project, packageNamePath, "src", "HeaderUtil.java", MAIN_JAVA);
    templateToExceptionHandler(project, packageNamePath, "src", "BadRequestAlertException.java", MAIN_JAVA);
    templateToExceptionHandler(project, packageNamePath, "src", "ErrorConstants.java", MAIN_JAVA);
    templateToExceptionHandler(project, packageNamePath, "src", "ExceptionTranslator.java", MAIN_JAVA);
    templateToExceptionHandler(project, packageNamePath, "src", "FieldErrorDTO.java", MAIN_JAVA);

    templateToExceptionHandler(project, packageNamePath, "test", "HeaderUtilTest.java", TEST_JAVA);
    templateToExceptionHandler(project, packageNamePath, "test", "BadRequestAlertExceptionTest.java", TEST_JAVA);
    templateToExceptionHandler(project, packageNamePath, "test", "ExceptionTranslatorIT.java", TEST_JAVA);
    templateToExceptionHandler(project, packageNamePath, "test", "ExceptionTranslatorTestController.java", TEST_JAVA);
    templateToExceptionHandler(project, packageNamePath, "test", "FieldErrorDTOTest.java", TEST_JAVA);

    projectRepository.template(project, getPath(SOURCE, "test"), "TestUtil.java", getPath(TEST_JAVA, packageNamePath));
  }

  private void addProperties(Project project) {
    springBootCommonService.addPropertiesComment(project, "Spring Boot Webflux");
    springBootCommonService.addProperties(project, "server.port", project.getServerPort());
    springBootCommonService.addPropertiesTest(project, "server.port", 0);
    springBootCommonService.addPropertiesNewLine(project);
  }

  private void addExceptionHandlerProperties(Project project) {
    String packageName = project.getPackageName().orElse(DefaultConfig.DEFAULT_PACKAGE_NAME);
    springBootCommonService.addProperties(project, "application.exception.details", "false");
    springBootCommonService.addProperties(project, "application.exception.package", "org.,java.,net.,javax.,com.,io.,de.," + packageName);
    springBootCommonService.addPropertiesTest(project, "application.exception.package", "org.,java.");
  }

  private void templateToExceptionHandler(Project project, String source, String type, String sourceFilename, String destination) {
    projectRepository.template(
      project,
      getPath(SOURCE, type),
      sourceFilename,
      getPath(destination, source, TECHNICAL_INFRASTRUCTURE_PRIMARY_EXCEPTION)
    );
  }
}
