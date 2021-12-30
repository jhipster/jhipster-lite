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
  void shouldInit() throws Exception {
    Project project = tmpProject();
    String issuerUri = "https://my/issuer/uri";

    GitUtils.init(project.getFolder());
    mavenService.addPomXml(project);
    javaBaseApplicationService.init(project);
    springBootService.init(project);
    springBootMvcService.init(project);

    oAuth2SecurityApplicationService.init(project, issuerUri);

    assertSecurityDependencies(project);
    assertOAuth2ClientDependencies(project);
    assertOAuth2ClientProperties(project, issuerUri);
  }
  // TODO test default
  // TODO test jwt
  // TODO test opaque-token
  // TODO test account
}
