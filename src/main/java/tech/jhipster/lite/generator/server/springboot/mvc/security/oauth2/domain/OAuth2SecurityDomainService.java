package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain;

import static tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2Security.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import tech.jhipster.lite.generator.buildtool.generic.domain.BuildToolService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.domain.ProjectRepository;
import tech.jhipster.lite.generator.server.springboot.properties.domain.SpringBootPropertiesService;

public class OAuth2SecurityDomainService implements OAuth2SecurityService {

  public static final String SOURCE = "server/springboot/mvc/security/oauth2";

  private final ProjectRepository projectRepository;
  private final BuildToolService buildToolService;
  private final SpringBootPropertiesService springBootPropertiesService;

  public OAuth2SecurityDomainService(
    ProjectRepository projectRepository,
    BuildToolService buildToolService,
    SpringBootPropertiesService springBootPropertiesService
  ) {
    this.projectRepository = projectRepository;
    this.buildToolService = buildToolService;
    this.springBootPropertiesService = springBootPropertiesService;
  }

  @Override
  public void init(Project project, OAuth2Provider provider, String issuerUri) {
    projectRepository.gitInit(project);
    addOAuth2ClientDependencies(project);
    addOAuth2ClientProperties(project, provider, issuerUri);
  }

  @Override
  public void addDefault(Project project) {
    // TODO default
  }

  @Override
  public void addJwt(Project project) {
    addOAuth2ResourceServerDependency(project);
    // TODO JWT
  }

  @Override
  public void addOpaqueToken(Project project) {
    addOAuth2ResourceServerDependency(project);
    // TODO opaque token
  }

  @Override
  public void addAccount(Project project) {
    // TODO account
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
        springBootPropertiesService.addProperties(project, k, v);
        springBootPropertiesService.addPropertiesTest(project, k, v);
      });
  }

  private Map<String, Object> oauth2ClientProperties(OAuth2Provider provider, String issuerUri) {
    Map<String, Object> result = new LinkedHashMap<>();

    OAuth2Provider providerFallback = Optional.ofNullable(provider).orElse(OAuth2Provider.KEYCLOAK);
    String providerId = providerFallback.name().toLowerCase();

    if (providerFallback.isCustom()) {
      String issuerUriFallback = Optional.ofNullable(issuerUri).orElse(providerFallback.getDefaultIssuerUri());
      result.put("spring.security.oauth2.client.provider." + providerId + ".issuer-uri", issuerUriFallback);
    }
    if (!providerFallback.isBuiltIn()) {
      result.put("spring.security.oauth2.client.registration." + providerId + ".client-name", providerId);
    }
    result.put("spring.security.oauth2.client.registration." + providerId + ".client-id", "web_app");
    result.put("spring.security.oauth2.client.registration." + providerId + ".client-secret", "web_app");
    result.put("spring.security.oauth2.client.registration." + providerId + ".scope", "openid,profile,email");

    return result;
  }

  private void addOAuth2ResourceServerDependency(Project project) {
    buildToolService.addDependency(project, springBootStarterOAuth2ResourceServerDependency());
  }
}
