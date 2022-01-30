package tech.jhipster.lite.generator.history.application;

import static org.assertj.core.api.Assertions.assertThat;
import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.history.domain.GeneratorHistoryValue;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
class GeneratorGeneratorHistoryApplicationServiceIT {

  @Autowired
  GeneratorHistoryApplicationService generatorHistoryApplicationService;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldAddHistoryValue() throws IOException {
    // Given
    Project project = tmpProject();
    project.addConfig(BASE_NAME, "foo");

    // When
    GeneratorHistoryValue generatorHistoryValue = new GeneratorHistoryValue().setServiceId("init-project");
    generatorHistoryApplicationService.addHistoryValue(project, generatorHistoryValue);

    // Then
    String content = FileUtils.read(getPath(project.getFolder(), ".jhipster", "history.json"));
    assertThat(content).isEqualTo(getExpectedHistoryFileContent());
  }

  private String getExpectedHistoryFileContent() {
    return """
      {
        "values" : [ {
          "serviceId" : "init-project"
        } ]
      }""";
  }
}
