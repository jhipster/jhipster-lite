package tech.jhipster.lite.generator.project.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static tech.jhipster.lite.TestUtils.tmpProjectWithPomXml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class ProjectApplicationServiceIT {

  @Autowired
  ProjectApplicationService projectApplicationService;

  @Test
  void shouldDownloadProject() {
    Project project = tmpProjectWithPomXml();

    assertThat(projectApplicationService.download(project)).isNotNull();
  }
}
