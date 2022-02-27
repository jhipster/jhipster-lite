package tech.jhipster.lite.generator.client.svelte.core.application;

import static tech.jhipster.lite.TestUtils.tmpProjectWithPackageJson;
import static tech.jhipster.lite.generator.client.svelte.core.application.SvelteAssert.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class SvelteApplicationServiceIT {

  @Autowired
  SvelteApplicationService svelteApplicationService;

  @Test
  void shouldAddSvelte() {
    Project project = tmpProjectWithPackageJson();

    svelteApplicationService.addSvelte(project);

    assertDependency(project);
    assertScripts(project);

    assertSvelteConfigFiles(project);
    assertRootFiles(project);
    assertAppFiles(project);

    assertJestSonar(project);
    assertType(project);
  }

  @Test
  void shouldAddStyledSvelte() {
    Project project = tmpProjectWithPackageJson();

    svelteApplicationService.addStyledSvelteKit(project);

    assertDependency(project);
    assertScripts(project);

    assertSvelteConfigFiles(project);
    assertRootFiles(project);
    assertAppFiles(project);
    assertAssets(project);

    assertJestSonar(project);
    assertType(project);
  }
}
