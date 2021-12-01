package tech.jhipster.light.generator.server.springboot.mvc.security.jwt.application;

import static tech.jhipster.light.TestUtils.*;
import static tech.jhipster.light.generator.buildtool.maven.domain.MavenDomainService.POM_XML;
import static tech.jhipster.light.generator.server.springboot.mvc.security.jwt.application.JwtSecurityAssertFiles.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.generator.buildtool.maven.domain.MavenService;
import tech.jhipster.light.generator.project.domain.Project;
import tech.jhipster.light.generator.project.infrastructure.secondary.GitUtils;
import tech.jhipster.light.generator.server.javatool.base.application.JavaBaseApplicationService;
import tech.jhipster.light.generator.server.springboot.core.domain.SpringBootService;
import tech.jhipster.light.generator.server.springboot.mvc.web.domain.SpringBootMvcService;

@IntegrationTest
class JwtSecurityApplicationServiceIT {

  @Autowired
  MavenService mavenService;

  @Autowired
  JavaBaseApplicationService javaBaseApplicationService;

  @Autowired
  SpringBootService springBootService;

  @Autowired
  SpringBootMvcService springBootMvcService;

  @Autowired
  JwtSecurityApplicationService jwtSecurityApplicationService;

  @Test
  void shouldInitBasicAuth() throws Exception {
    Project project = tmpProject();
    GitUtils.init(project.getFolder());
    mavenService.addPomXml(project);
    javaBaseApplicationService.init(project);
    springBootService.init(project);
    springBootMvcService.init(project);

    jwtSecurityApplicationService.initBasicAuth(project);

    assertPomXmlProperties(project);
    assertJwtSecurityFilesExists(project);
    assertJwtSecurityProperties(project);
    assertGitPatch(project);
  }
}
