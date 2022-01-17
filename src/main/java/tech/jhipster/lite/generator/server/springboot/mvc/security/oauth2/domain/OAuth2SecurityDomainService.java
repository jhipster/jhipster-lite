package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_PATH;
import static tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2Security.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.common.domain.SpringBootCommonService;

public class OAuth2SecurityDomainService implements OAuth2SecurityService {

  public static final String SOURCE = "server/springboot/mvc/security/oauth2";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootCommonService springBootCommonService;

  public OAuth2SecurityDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
  }

  @Override
  public void addClient(Project project, OAuth2Provider provider, String issuerUri) {
    addCommons(project, provider, issuerUri);
  }

  @Override
  public void addDefault(Project project, OAuth2Provider provider, String issuerUri) {
    addCommons(project, provider, issuerUri);
    // TODO default security configuration
  }

  @Override
  public void addKeycloakDocker(Project project) {
    project.addConfig("dockerKeycloakImage", getDockerKeycloakImage());
    project.addConfig("dockerKeycloakVersion", getDockerKeycloakVersion());
    projectRepository.template(project, getPath(SOURCE, "src"), "keycloak.yml", "src/main/docker", "keycloak.yml");
    projectRepository.template(
      project,
      getPath(SOURCE, "src"),
      "jhipster-realm.json.mustache",
      "src/main/docker/keycloak-realm-config",
      "jhipster-realm.json"
    );
    projectRepository.template(
      project,
      getPath(SOURCE, "src"),
      "jhipster-users-0.json.mustache",
      "src/main/docker/keycloak-realm-config",
      "jhipster-users-0.json"
    );
  }

  private void addCommons(Project project, OAuth2Provider provider, String issuerUri) {
    addOAuth2ClientDependencies(project);
    OAuth2Provider providerFallback = fallbackToDefault(provider);
    addOAuth2ClientProperties(project, providerFallback, issuerUri);
    updateExceptionTranslator(project);
    if (providerFallback == OAuth2Provider.KEYCLOAK) {
      addKeycloakDocker(project);
    }
  }

  private OAuth2Provider fallbackToDefault(OAuth2Provider provider) {
    return Optional.ofNullable(provider).orElse(DEFAULT_PROVIDER);
  }

  private void addOAuth2ClientDependencies(Project project) {
    buildToolService.addDependency(project, springBootStarterSecurityDependency());
    buildToolService.addDependency(project, springBootStarterOAuth2ClientDependency());
    // test
    buildToolService.addDependency(project, springSecurityTestDependency());
  }

  private void addOAuth2ClientProperties(Project project, OAuth2Provider provider, String issuerUri) {
    oauth2ClientProperties(provider, issuerUri)
      .forEach((k, v) -> {
        springBootCommonService.addProperties(project, k, v);
        springBootCommonService.addPropertiesTest(project, k, v);
      });
  }

  private Map<String, Object> oauth2ClientProperties(OAuth2Provider provider, String issuerUri) {
    Map<String, Object> result = new LinkedHashMap<>();

    String providerId = provider.name().toLowerCase();

    if (provider.isCustom()) {
      String issuerUriFallback = Optional.ofNullable(issuerUri).orElse(provider.getDefaultIssuerUri());
      result.put("spring.security.oauth2.client.provider." + providerId + ".issuer-uri", issuerUriFallback);
    }
    if (!provider.isBuiltIn()) {
      result.put("spring.security.oauth2.client.registration." + providerId + ".client-name", providerId);
    }
    result.put("spring.security.oauth2.client.registration." + providerId + ".client-id", "web_app");
    result.put("spring.security.oauth2.client.registration." + providerId + ".client-secret", "web_app");
    result.put("spring.security.oauth2.client.registration." + providerId + ".scope", "openid,profile,email");

    return result;
  }

  private void updateExceptionTranslator(Project project) {
    String packageNamePath = project.getPackageNamePath().orElse(getPath(PACKAGE_PATH));
    String exceptionPath = getPath(TEST_JAVA, packageNamePath, "technical/infrastructure/primary/exception");

    // TODO test CSRF

    // import @WithMockUser
    String oldImport = "import org.springframework.test.util.ReflectionTestUtils;";
    String newImport =
      """

      import org.springframework.security.test.context.support.WithMockUser;
      import org.springframework.test.util.ReflectionTestUtils;""";
    projectRepository.replaceText(project, exceptionPath, "ExceptionTranslatorIT.java", oldImport, newImport);

    // add annotations
    String oldAnnotation = "@AutoConfigureMockMvc";
    String newAnnotation = """
      @AutoConfigureMockMvc
      @WithMockUser""";
    projectRepository.replaceText(project, exceptionPath, "ExceptionTranslatorIT.java", oldAnnotation, newAnnotation);
  }
}
