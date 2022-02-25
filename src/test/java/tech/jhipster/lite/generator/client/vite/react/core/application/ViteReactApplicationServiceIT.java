package tech.jhipster.lite.generator.client.vite.react.core.application;

import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class ViteReactApplicationServiceIT {

  @Autowired
  ViteReactApplicationService viteReactApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPackageJsonComplete();

    viteReactApplicationService.addViteReact(project);

    ViteReactAssert.assertDependency(project);
    ViteReactAssert.assertScripts(project);
    ViteReactAssert.assertConfig(project);
    ViteReactAssert.assertFiles(project);
    ViteReactAssert.assertReactFiles(project);
    ViteReactAssert.assertJestSonar(project);
  }

  @Test
  void shouldInitStyled() {
    Project project = tmpProjectWithPackageJsonComplete();

    viteReactApplicationService.addStyledViteReact(project);
    ViteReactAssert.assertDependency(project);
    ViteReactAssert.assertScripts(project);
    ViteReactAssert.assertConfig(project);
    ViteReactAssert.assertFiles(project);
    ViteReactAssert.assertReactFiles(project);
    ViteReactAssert.assertJestSonar(project);
  }
}
