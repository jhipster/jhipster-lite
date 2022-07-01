package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;
import static tech.jhipster.lite.generator.server.springboot.springcloud.gateway.application.GatewayAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;
import tech.jhipster.lite.module.infrastructure.secondary.TestJHipsterModules;

@IntegrationTest
class GatewayApplicationServiceIT {

  @Autowired
  private GatewayApplicationService gatewayApplicationService;

  @Autowired
  private MavenApplicationService mavenApplicationService;

  @Autowired
  private SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "Foo");

    TestJHipsterModules.applyInit(project);

    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    gatewayApplicationService.init(project);

    assertDependencies(project);
    assertProperties(project);
    assertJavaFiles(project);
  }
}
