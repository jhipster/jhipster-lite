package tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_PATH;
import static tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.domain.JwtSecurity.*;

import java.util.LinkedHashMap;
import java.util.Map;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

public class JwtSecurityDomainService implements JwtSecurityService {

  public static final String SOURCE = "server/springboot/mvc/security/jwt";
  public static final String SECURITY_JWT_PATH = "security/jwt";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootPropertiesService springBootPropertiesService;

  public JwtSecurityDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Override
  public void init(Project project) {
    projectRepository.gitInit(project);

    addPropertyAndDependency(project);
    addJavaFiles(project);
    addProperties(project);
    addGitPatch(project);

    updateExceptionTranslator(project);
  }

  @Override
  public void addBasicAuth(Project project) {
    addBasicAuthJavaFiles(project);
    addBasicAuthProperties(project);
  }

  private void updateExceptionTranslator(Project project) {
    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));
    String exceptionPath = getPath(TEST_JAVA, packageNamePath, "technical/infrastructure/primary/exception");

    String oldImport = "import org.springframework.test.util.ReflectionTestUtils;";
    String newImport =
      """
      import org.springframework.security.test.context.support.WithMockUser;
      import org.springframework.test.util.ReflectionTestUtils;""";
    projectRepository.replaceText(project, exceptionPath, "ExceptionTranslatorIT.java", oldImport, newImport);

    String oldAnnotation = "@AutoConfigureMockMvc";
    String newAnnotation = """
      @AutoConfigureMockMvc
      @WithMockUser""";
    projectRepository.replaceText(project, exceptionPath, "ExceptionTranslatorIT.java", oldAnnotation, newAnnotation);
  }

  private void addGitPatch(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));
    project.addConfig("packageNamePath", packageNamePath);
  }

  private void addPropertyAndDependency(Project project) {
    buildToolService.addProperty(project, "jjwt", jjwtVersion());

    buildToolService.addDependency(project, springBootStarterSecurityDependency());
    buildToolService.addDependency(project, jjwtApiDependency());
    buildToolService.addDependency(project, jjwtImplDependency());
    buildToolService.addDependency(project, jjwtJacksonDependency());

    buildToolService.addDependency(project, springSecurityTestDependency());
  }

  private void addJavaFiles(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);

    String sourceSrc = getPath(SOURCE, "src");
    String sourceTest = getPath(SOURCE, "test");
    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));
    String destinationSrc = getPath(MAIN_JAVA, packageNamePath, SECURITY_JWT_PATH);
    String destinationTest = getPath(TEST_JAVA, packageNamePath, SECURITY_JWT_PATH);

    jwtSecurityFiles()
      .forEach((javaFile, destination) -> projectRepository.template(project, sourceSrc, javaFile, getPath(destinationSrc, destination)));

    jwtTestSecurityFiles()
      .forEach((javaFile, destination) -> projectRepository.template(project, sourceTest, javaFile, getPath(destinationTest, destination)));
  }

  private void addBasicAuthJavaFiles(Project project) {
    project.addDefaultConfig(PACKAGE_NAME);
    String sourceSrc = getPath(SOURCE, "src/account");
    String sourceTest = getPath(SOURCE, "test/account");

    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));
    String destinationSrc = getPath(MAIN_JAVA, packageNamePath, "account/infrastructure/primary/rest");
    String destinationTest = getPath(TEST_JAVA, packageNamePath, "account/infrastructure/primary/rest");

    projectRepository.template(project, sourceSrc, "AuthenticationResource.java", getPath(destinationSrc));
    projectRepository.template(project, sourceSrc, "AccountResource.java", getPath(destinationSrc));
    projectRepository.template(project, sourceSrc, "LoginDTO.java", getPath(destinationSrc));
    projectRepository.template(project, sourceTest, "AuthenticationResourceIT.java", getPath(destinationTest));
    projectRepository.template(project, sourceTest, "AccountResourceIT.java", getPath(destinationTest));
    projectRepository.template(project, sourceTest, "LoginDTOTest.java", getPath(destinationTest));
  }

  private void addBasicAuthProperties(Project project) {
    springBootPropertiesService.addProperties(project, "spring.security.user.name", "admin");
    springBootPropertiesService.addProperties(
      project,
      "spring.security.user.password",
      "\\$2a\\$12\\$cRKS9ZURbdJIaRsKDTDUmOrH4.B.2rokv8rrkrQXr2IR2Hkna484O"
    );
    springBootPropertiesService.addProperties(project, "spring.security.user.roles", "ADMIN");

    springBootPropertiesService.addPropertiesTest(project, "spring.security.user.name", "admin");
    springBootPropertiesService.addPropertiesTest(
      project,
      "spring.security.user.password",
      "\\$2a\\$12\\$cRKS9ZURbdJIaRsKDTDUmOrH4.B.2rokv8rrkrQXr2IR2Hkna484O"
    );
    springBootPropertiesService.addPropertiesTest(project, "spring.security.user.roles", "ADMIN");
  }

  private void addProperties(Project project) {
    String baseName = project.getBaseName().orElse("jhipster");

    jwtProperties(baseName)
      .forEach((k, v) -> {
        springBootPropertiesService.addProperties(project, k, v);
        springBootPropertiesService.addPropertiesTest(project, k, v);
      });
  }

  private Map<String, Object> jwtProperties(String baseName) {
    Map<String, Object> result = new LinkedHashMap<>();
    result.put(
      "application.security.authentication.jwt.base64-secret",
      "bXktc2VjcmV0LWtleS13aGljaC1zaG91bGQtYmUtY2hhbmdlZC1pbi1wcm9kdWN0aW9uLWFuZC1iZS1iYXNlNjQtZW5jb2RlZAo="
    );
    result.put("application.security.authentication.jwt.token-validity-in-seconds", "86400");
    result.put("application.security.authentication.jwt.token-validity-in-seconds-for-remember-me", "2592000");
    result.put("application.cors.allowed-origins", "http://localhost:8100,http://localhost:9000");
    result.put("application.cors.allowed-methods", "*");
    result.put("application.cors.allowed-headers", "*");
    result.put(
      "application.cors.exposed-headers",
      "Authorization,Link,X-Total-Count,X-" + baseName + "-alert,X-" + baseName + "-error,X-" + baseName + "-params"
    );
    result.put("application.cors.allow-credentials", "true");
    result.put("application.cors.max-age", "1800");
    return result;
  }
}
