package tech.jhipster.lite.generator.server.springboot.springcloud.consul.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.*;
import static tech.jhipster.lite.generator.server.springboot.springcloud.consul.application.ConsulAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;
import tech.jhipster.lite.module.infrastructure.secondary.TestJHipsterModules;

@IntegrationTest
class ConsulApplicationServiceIT {

  @Autowired
  private ConsulApplicationService consulApplicationService;

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

    consulApplicationService.init(project);

    assertDependencies(project);
    assertProperties(project);
    assertDockerConsul(project);
  }

  @Test
  void shouldAddDependencies() {
    Project project = tmpProject();
    TestJHipsterModules.applyInit(project);
    mavenApplicationService.addPomXml(project);

    consulApplicationService.addDependencies(project);

    assertDependencies(project);
  }

  @Test
  void shouldAddProperties() {
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "bar");
    TestJHipsterModules.applyInit(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    consulApplicationService.addProperties(project);

    assertProperties(project);
  }

  @Test
  void shouldAddDockerConsul() {
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "bar");
    TestJHipsterModules.applyInit(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    consulApplicationService.addDockerConsul(project);

    assertDockerConsul(project);
  }
}
