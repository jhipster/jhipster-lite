package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_PATH;
import static tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2Security.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
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

  public OAuth2SecurityDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootCommonService springBootCommonService,
    CommonSecurityService commonSecurityService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootCommonService = springBootCommonService;
    this.commonSecurityService = commonSecurityService;
  }

  @Override
  public void addOAuth2(Project project) {
    addDependencies(project);
    addKeycloakDocker(project);
    addJavaFiles(project);
    addSpringBootProperties(project);

    updateExceptionTranslator(project);
    updateIntegrationTestWithMockUser(project);
  }

  private void addDependencies(Project project) {
    buildToolService.addDependency(project, springBootStarterSecurityDependency());
    buildToolService.addDependency(project, springBootStarterOAuth2ClientDependency());
    buildToolService.addDependency(project, springSecurityTestDependency());
    buildToolService.addDependency(project, springBootStarterOAuth2ResourceServerDependency());
  }

  private void addKeycloakDocker(Project project) {
    project.addConfig("dockerKeycloakImage", getDockerKeycloakImage());
    project.addConfig("dockerKeycloakVersion", getDockerKeycloakVersion());

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

  public void addClient(Project project, OAuth2Provider provider, String issuerUri) {
    addCommons(project, provider, issuerUri);
  }

  public void addDefault(Project project, OAuth2Provider provider, String issuerUri) {
    addCommons(project, provider, issuerUri);
    addResourceServerJwt(project);
    // TODO default security configuration
  }

  public void addResourceServerJwt(Project project) {
    addOAuth2ResourceServerDependency(project);
    // TODO JWT
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

  private void addOAuth2ResourceServerDependency(Project project) {
    buildToolService.addDependency(project, springBootStarterOAuth2ResourceServerDependency());
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
}
