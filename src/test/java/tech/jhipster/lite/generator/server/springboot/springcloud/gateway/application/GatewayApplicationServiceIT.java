package tech.jhipster.lite.generator.server.springboot.springcloud.gateway.application;

import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.server.springboot.springcloud.gateway.application.GatewayAssert.assertDependencies;
import static tech.jhipster.lite.generator.server.springboot.springcloud.gateway.application.GatewayAssert.assertProperties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
class GatewayApplicationServiceIT {

  @Autowired
  GatewayApplicationService gatewayApplicationService;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "Foo");

    initApplicationService.init(project);

    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    gatewayApplicationService.init(project);

    assertDependencies(project);
    assertProperties(project);
  }
}
