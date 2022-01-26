package tech.jhipster.lite.generator.client.angular.core.application;

import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class AngularApplicationServiceIT {

  @Autowired
  AngularApplicationService angularApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProjectWithPackageJsonComplete();

    angularApplicationService.init(project);

    AngularAssert.assertDevDependencies(project);
    AngularAssert.assertDependencies(project);
    AngularAssert.assertScripts(project);
    AngularAssert.assertConfigFiles(project);
    AngularAssert.assertAngularFiles(project);
  }
}
