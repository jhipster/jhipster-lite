package tech.jhipster.forge.generator.domain.server.tool.error;

import static tech.jhipster.forge.generator.domain.core.Constants.MAIN_JAVA;
import static tech.jhipster.forge.generator.domain.core.Constants.TEST_JAVA;
import static tech.jhipster.forge.generator.domain.core.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.forge.generator.domain.core.FileUtils.getPath;

import tech.jhipster.forge.generator.domain.core.Project;
import tech.jhipster.forge.generator.domain.core.ProjectRepository;

public class ErrorDomainService implements ErrorService {

  public static final String SOURCE = "error";

  private final ProjectRepository projectRepository;

  public ErrorDomainService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void init(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));
    String errorDomainPath = "error/domain";

    projectRepository.template(project, SOURCE, "Assert.java", getPath(MAIN_JAVA, packageNamePath, errorDomainPath));
    projectRepository.template(
      project,
      SOURCE,
      "MissingMandatoryValueException.java",
      getPath(MAIN_JAVA, packageNamePath, errorDomainPath)
    );
    projectRepository.template(project, SOURCE, "UnauthorizedValueException.java", getPath(MAIN_JAVA, packageNamePath, errorDomainPath));

    projectRepository.template(project, SOURCE, "AssertTest.java", getPath(TEST_JAVA, packageNamePath, errorDomainPath));
    projectRepository.template(
      project,
      SOURCE,
      "MissingMandatoryValueExceptionTest.java",
      getPath(TEST_JAVA, packageNamePath, errorDomainPath)
    );
    projectRepository.template(
      project,
      SOURCE,
      "UnauthorizedValueExceptionTest.java",
      getPath(TEST_JAVA, packageNamePath, errorDomainPath)
    );
  }
}
