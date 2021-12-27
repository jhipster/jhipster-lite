package tech.jhipster.lite.generator.server.springboot.common.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
class SpringBootCommonApplicationServiceIT {

  @Autowired
  SpringBootCommonApplicationService springBootCommonApplicationService;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldAddTestLogbackRecorder() {
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "foo");
    project.addConfig(PACKAGE_NAME, "tech.jhipster.bar");
    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    springBootCommonApplicationService.addTestLogbackRecorder(project);

    String packageName = project.getPackageName().orElse("com.mycompany.myapp");
    String packageNamePath = project.getPackageNamePath().orElse(getPath("com/mycompany/myapp"));

    assertFileExist(project, getPath(TEST_JAVA, packageNamePath, "LogbackRecorder.java"));

    assertFileContent(project, getPath(TEST_JAVA, packageNamePath, "LogbackRecorder.java"), "package " + packageName);
  }
}
