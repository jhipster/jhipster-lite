package tech.jhipster.lite.generator.client.common.cypress.application;

import static tech.jhipster.lite.TestUtils.tmpProjectWithPackageJsonComplete;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class CypressApplicationServiceIT {

  @Autowired
  CypressApplicationService cypressApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPackageJsonComplete();

    cypressApplicationService.init(project);

    CypressAssert.assertDependency(project);
    CypressAssert.assertCypressScripts(project);
    CypressAssert.assertCypressFiles(project);
    CypressAssert.assertCypressFiles(project);
    CypressAssert.assertCypressTestFiles(project);
  }
}
