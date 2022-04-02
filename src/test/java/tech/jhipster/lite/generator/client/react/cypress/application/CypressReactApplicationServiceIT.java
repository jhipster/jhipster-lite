package tech.jhipster.lite.generator.client.react.cypress.application;

import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class CypressReactApplicationServiceIT {

  @Autowired
  CypressReactApplicationService CypressReactApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPackageJsonComplete();

    CypressReactApplicationService.init(project);

    CypressReactAssert.assertDependency(project);
    CypressReactAssert.assertCypressScripts(project);
    CypressReactAssert.assertCypressFiles(project);
    CypressReactAssert.assertCypressFiles(project);
    CypressReactAssert.assertCypressTestFiles(project);
  }
}
