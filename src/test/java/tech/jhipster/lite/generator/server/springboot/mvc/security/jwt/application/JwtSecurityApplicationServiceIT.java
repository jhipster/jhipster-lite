package tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.server.springboot.mvc.security.jwt.application.JwtSecurityAssertFiles.*;

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

    String exceptionTranslatorIT = "com/mycompany/myapp/technical/infrastructure/primary/exception/ExceptionTranslatorIT.java";
    assertFileContent(
      project,
      getPath(TEST_JAVA, exceptionTranslatorIT),
      "import org.springframework.security.test.context.support.WithMockUser;"
    );
    assertFileContent(project, getPath(TEST_JAVA, exceptionTranslatorIT), "@WithMockUser");
  }
}
