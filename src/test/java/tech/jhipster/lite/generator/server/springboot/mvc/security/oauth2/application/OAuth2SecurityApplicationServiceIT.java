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
  void shouldInitDefaultProvider() throws Exception {
    init(null, null);
  }

  @Test
  void shouldInitGoogle() throws Exception {
    init(OAuth2Provider.GOOGLE, null);
  }

  @Test
  void shouldInitFacebook() throws Exception {
    init(OAuth2Provider.FACEBOOK, null);
  }

  @Test
  void shouldInitGithub() throws Exception {
    init(OAuth2Provider.GITHUB, null);
  }

  @Test
  void shouldInitOkta() throws Exception {
    init(OAuth2Provider.OKTA, null);
  }

  @Test
  void shouldInitKeycloak() throws Exception {
    init(OAuth2Provider.KEYCLOAK, null);
  }

  @Test
  void shouldInitAuth0() throws Exception {
    init(OAuth2Provider.AUTHO0, null);
  }

  @Test
  void shouldInitOther() throws Exception {
    init(OAuth2Provider.OTHER, null);
  }

  @Test
  void shouldInitOtherWithCustomIssuerUri() throws Exception {
    String issuerUri = "https://my/issuer/uri";
    init(OAuth2Provider.OTHER, issuerUri);
  }

  private void init(OAuth2Provider provider, String issuerUri) throws Exception {
    Project project = tmpProject();

    GitUtils.init(project.getFolder());
    mavenService.addPomXml(project);
    javaBaseApplicationService.init(project);
    springBootService.init(project);
    springBootMvcService.init(project);

    oAuth2SecurityApplicationService.init(project, provider, issuerUri);

    assertSecurityDependencies(project);
    assertOAuth2ClientDependencies(project);
    assertOAuth2ClientProperties(project, provider, issuerUri);
  }
  // TODO test default
  // TODO test jwt
  // TODO test opaque-token
  // TODO test account
}
