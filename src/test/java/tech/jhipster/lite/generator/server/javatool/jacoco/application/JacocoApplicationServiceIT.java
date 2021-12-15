package tech.jhipster.lite.generator.server.javatool.jacoco.application;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class JacocoApplicationServiceIT {

  @Autowired
  JacocoApplicationService jacocoApplicationService;

  @Test
  void shouldAddCheckMinimumCoverage() {
    Project project = TestUtils.tmpProjectWithPomXml();

    jacocoApplicationService.addCheckMinimumCoverage(project);

    TestUtils.assertFileContent(
      project,
      "pom.xml",
      List.of("<counter>LINE</counter>", "<value>COVEREDRATIO</value>", "<minimum>1.00</minimum>")
    );
  }
}
