package tech.jhipster.lite.generator.client.vite.react.cypress.application;

import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class CypressViteReactApplicationServiceIT {

  @Autowired
  CypressViteReactApplicationService cypressViteReactApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPackageJsonComplete();

    cypressViteReactApplicationService.init(project);

    CypressViteReactAssert.assertDependency(project);
    CypressViteReactAssert.assertCypressScripts(project);
    CypressViteReactAssert.assertCypressFiles(project);
    CypressViteReactAssert.assertCypressFiles(project);
    CypressViteReactAssert.assertCypressTestFiles(project);
  }
}
