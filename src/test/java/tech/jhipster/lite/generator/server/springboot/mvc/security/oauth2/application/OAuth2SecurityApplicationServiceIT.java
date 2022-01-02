package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.application;

import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.application.OAuth2SecurityAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.secondary.GitUtils;
import tech.jhipster.lite.generator.server.javatool.base.application.JavaBaseApplicationService;
import tech.jhipster.lite.generator.server.springboot.core.domain.SpringBootService;
import tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.domain.OAuth2Provider;
import tech.jhipster.lite.generator.server.springboot.mvc.web.domain.SpringBootMvcService;

@IntegrationTest
class OAuth2SecurityApplicationServiceIT {

  @Autowired
  MavenService mavenService;

  @Autowired
  JavaBaseApplicationService javaBaseApplicationService;

  @Autowired
  SpringBootService springBootService;

  @Autowired
  SpringBootMvcService springBootMvcService;

  @Autowired
  OAuth2SecurityApplicationService oAuth2SecurityApplicationService;

  @Test
  void shouldAddClientDefault() throws Exception {
    shouldAddClient(null, null);
  }

  @Test
  void shouldAddClientGoogle() throws Exception {
    shouldAddClient(OAuth2Provider.GOOGLE, null);
  }

  @Test
  void shouldAddClientFacebook() throws Exception {
    shouldAddClient(OAuth2Provider.FACEBOOK, null);
  }

  @Test
  void shouldAddClientGithub() throws Exception {
    shouldAddClient(OAuth2Provider.GITHUB, null);
  }

  @Test
  void shouldAddClientOkta() throws Exception {
    shouldAddClient(OAuth2Provider.OKTA, null);
  }

  @Test
  void shouldAddClientKeycloak() throws Exception {
    shouldAddClient(OAuth2Provider.KEYCLOAK, null);
  }

  @Test
  void shouldAddClientAuth0() throws Exception {
    shouldAddClient(OAuth2Provider.AUTHO0, null);
  }

  @Test
  void shouldAddClientOther() throws Exception {
    shouldAddClient(OAuth2Provider.OTHER, null);
  }

  @Test
  void shouldAddClientOtherWithCustomIssuerUri() throws Exception {
    String issuerUri = "https://my/issuer/uri";
    shouldAddClient(OAuth2Provider.OTHER, issuerUri);
  }

  @Test
  void shouldAddDefault() throws Exception {
    Project project = tmpProject();

    GitUtils.init(project.getFolder());
    mavenService.addPomXml(project);
    javaBaseApplicationService.init(project);
    springBootService.init(project);
    springBootMvcService.init(project);

    oAuth2SecurityApplicationService.addClient(project, null, null);

    assertSecurityDependencies(project);
    assertOAuth2ClientDependencies(project);
    assertOAuth2ClientProperties(project, null, null);
    // TODO assert default security configuration
  }

  private void shouldAddClient(OAuth2Provider provider, String issuerUri) throws Exception {
    Project project = tmpProject();

    GitUtils.init(project.getFolder());
    mavenService.addPomXml(project);
    javaBaseApplicationService.init(project);
    springBootService.init(project);
    springBootMvcService.init(project);

    oAuth2SecurityApplicationService.addClient(project, provider, issuerUri);

    assertSecurityDependencies(project);
    assertOAuth2ClientDependencies(project);
    assertOAuth2ClientProperties(project, provider, issuerUri);
    if (provider == null || provider == OAuth2Provider.KEYCLOAK) {
      assertDockerKeycloak(project);
    }
  }
}
