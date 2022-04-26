package tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.application;

import static tech.jhipster.lite.common.domain.FileUtils.tmpDirForTest;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.BASE_NAME;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.application.MongockAssert.assertConfigAndChangeLogFiles;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.application.MongockAssert.assertDependencies;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.application.MongockAssert.assertProperties;
import static tech.jhipster.lite.generator.server.springboot.dbmigration.mongock.application.MongockAssert.assertTestFiles;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.TestUtils;
import tech.jhipster.lite.generator.buildtool.maven.application.MavenApplicationService;
import tech.jhipster.lite.generator.init.application.InitApplicationService;
import tech.jhipster.lite.generator.project.domain.Project;
import tech.jhipster.lite.generator.project.infrastructure.primary.dto.ProjectDTO;
import tech.jhipster.lite.generator.server.springboot.core.application.SpringBootApplicationService;

@IntegrationTest
class MongockApplicationServiceIT {

  @Autowired
  MongockApplicationService mongockApplicationService;

  @Autowired
  InitApplicationService initApplicationService;

  @Autowired
  MavenApplicationService mavenApplicationService;

  @Autowired
  SpringBootApplicationService springBootApplicationService;

  @Test
  void shouldInit() {
    // Given
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class).folder(tmpDirForTest());
    Project project = ProjectDTO.toProject(projectDTO);
    project.addConfig(BASE_NAME, "foo");

    initApplicationService.init(project);
    mavenApplicationService.addPomXml(project);
    springBootApplicationService.init(project);

    // When
    mongockApplicationService.init(project);

    // Then
    assertDependencies(project);
    assertConfigAndChangeLogFiles(project);
    assertTestFiles(project);
    assertProperties(project);
  }
}
