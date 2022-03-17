package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;
import static tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2Security.*;

import tech.jhipster.lite.common.domain.WordUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.docker.domain.DockerService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;
import tech.jhipster.lite.generator.server.springboot.mvc.security.common.domain.CommonSecurityService;

public class OAuth2SecurityDomainService implements OAuth2SecurityService {

  public static final String SOURCE = "server/springboot/mvc/security/oauth2";
  public static final String SECURITY_OAUTH2_PATH = "security/oauth2";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;
  private final CommonSecurityService commonSecurityService;
  private final DockerService dockerService;

  public OAuth2SecurityDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService,
    CommonSecurityService commonSecurityService,
    DockerService dockerService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
    this.commonSecurityService = commonSecurityService;
    this.dockerService = dockerService;
  }

  @Override
  public void addOAuth2(Project project) {
    addDependencies(project);
    addKeycloakDocker(project);
    addJavaFiles(project);
    addSpringBootProperties(project);

    updateExceptionTranslator(project);
    updateIntegrationTestWithMockUser(project);
    updateIntegrationTestWithTestSecurityConfiguration(project);
  }

  private void addDependencies(Project project) {
    buildToolService.addDependency(project, springBootStarterSecurityDependency());
    buildToolService.addDependency(project, springBootStarterOAuth2ClientDependency());
    buildToolService.addDependency(project, springSecurityTestDependency());
    buildToolService.addDependency(project, springBootStarterOAuth2ResourceServerDependency());
  }

  private void addKeycloakDocker(Project project) {
    String keycloakVersion = dockerService
      .getImageVersion(getDockerKeycloakImageName())
      .orElseThrow(() -> {
        throw new GeneratorException("Version not found for docker image: " + getDockerKeycloakImageName());
      });
    String imageName = dockerService.getImageNameWithVersion(getDockerKeycloakImageName()).orElseThrow();

    project.addConfig("dockerKeycloakImage", imageName);
    project.addConfig("dockerKeycloakVersion", keycloakVersion);

    String dockerSourcePath = getPath(SOURCE, "docker");
    String dockerPathRealm = getPath(MAIN_DOCKER, "keycloak-realm-config");
    projectRepository.template(project, dockerSourcePath, "keycloak.yml", MAIN_DOCKER, "keycloak.yml");
    projectRepository.template(project, dockerSourcePath, "jhipster-realm.json", dockerPathRealm, "jhipster-realm.json");
    projectRepository.template(project, dockerSourcePath, "jhipster-users-0.json", dockerPathRealm, "jhipster-users-0.json");
  }

  private void addJavaFiles(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));

    String sourceSrc = getPath(SOURCE, "src");
    String destinationSrc = getPath(MAIN_JAVA, packageNamePath, SECURITY_OAUTH2_PATH);
    oauth2SecurityFiles()
      .forEach((javaFile, folder) ->
        projectRepository.template(project, getPath(sourceSrc, folder), javaFile, getPath(destinationSrc, folder))
      );

    String sourceTest = getPath(SOURCE, "test");
    String destinationTest = getPath(TEST_JAVA, packageNamePath, SECURITY_OAUTH2_PATH);
    oauth2TestSecurityFiles()
      .forEach((javaFile, folder) ->
        projectRepository.template(project, getPath(sourceTest, folder), javaFile, getPath(destinationTest, folder))
      );
  }

  @Override
  public void addAccountContext(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));

    String sourceSrc = getPath(SOURCE, ACCOUNT_CONTEXT, "src");
    String destinationSrc = getPath(MAIN_JAVA, packageNamePath);
    oauth2AccountContextFiles()
      .forEach((file, folder) -> projectRepository.template(project, getPath(sourceSrc, folder), file, getPath(destinationSrc, folder)));

    String sourceTest = getPath(SOURCE, ACCOUNT_CONTEXT, "test");
    String destinationTest = getPath(TEST_JAVA, packageNamePath);
    oauth2AccountContextTestFiles()
      .forEach((file, folder) -> projectRepository.template(project, getPath(sourceTest, folder), file, getPath(destinationTest, folder)));
  }

  private void addSpringBootProperties(Project project) {
    springBootCommonService.addPropertiesComment(project, "Spring Security OAuth2");
    properties().forEach((k, v) -> springBootCommonService.addProperties(project, k, v));
    springBootCommonService.addPropertiesNewLine(project);

    springBootCommonService.addPropertiesTestComment(project, "Spring Security OAuth2");
    propertiesForTests().forEach((k, v) -> springBootCommonService.addPropertiesTest(project, k, v));
    springBootCommonService.addPropertiesTestNewLine(project);
  }

  private void updateExceptionTranslator(Project project) {
    commonSecurityService.updateExceptionTranslator(project);
  }

  private void updateIntegrationTestWithMockUser(Project project) {
    commonSecurityService.updateIntegrationTestWithMockUser(project);
  }

  private void updateIntegrationTestWithTestSecurityConfiguration(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageName = project.getPackageName().orElse(DEFAULT_PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));
    String integrationTestPath = getPath(TEST_JAVA, packageNamePath);

    project.addDefaultConfig(BASE_NAME);
    String baseName = project.getBaseName().orElse(DEFAULT_BASE_NAME);
    String className = WordUtils.upperFirst(baseName);

    String oldImport = "import org.springframework.boot.test.context.SpringBootTest;";
    String newImport = String.format(
      """
        import org.springframework.boot.test.context.SpringBootTest;
        import %s.security.oauth2.infrastructure.config.TestSecurityConfiguration;""",
      packageName
    );
    projectRepository.replaceText(project, integrationTestPath, "IntegrationTest.java", oldImport, newImport);

    String oldClass = className + "App.class";
    String newClass = className + "App.class, TestSecurityConfiguration.class";
    projectRepository.replaceText(project, integrationTestPath, "IntegrationTest.java", oldClass, newClass);
  }
}
