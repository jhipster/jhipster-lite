package tech.jhipster.lite.generator.server.springboot.mvc.dummy.application;

import static tech.jhipster.lite.TestUtils.*;
import static tech.jhipster.lite.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.lite.generator.project.domain.Constants.TEST_JAVA;
import static tech.jhipster.lite.generator.project.domain.DefaultConfig.PACKAGE_NAME;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class DummyApplicationServiceIT {

  @Autowired
  DummyApplicationService dummyApplicationService;

  @Test
  void shouldApplyDummyGitPatch() {
    Project project = tmpProject();

    dummyApplicationService.applyDummyGitPatch(project);

    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, "com/mycompany/myapp/dummy/application/DummyApplicationService.java"),
      "package com.mycompany.myapp.dummy.application;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, "com/mycompany/myapp/dummy/domain/Dummy.java"),
      "package com.mycompany.myapp.dummy.domain;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, "com/mycompany/myapp/dummy/domain/DummyRepository.java"),
      "package com.mycompany.myapp.dummy.domain;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, "com/mycompany/myapp/dummy/infrastructure/primary/rest/DummyResource.java"),
      "package com.mycompany.myapp.dummy.infrastructure.primary.rest;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, "com/mycompany/myapp/dummy/infrastructure/secondary/DummyInMemoryRepository.java"),
      "package com.mycompany.myapp.dummy.infrastructure.secondary;"
    );

    assertFileContent(
      project,
      FileUtils.getPath(TEST_JAVA, "com/mycompany/myapp/dummy/application/DummyApplicationServiceIT.java"),
      "package com.mycompany.myapp.dummy.application;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(TEST_JAVA, "com/mycompany/myapp/dummy/domain/DummyTest.java"),
      "package com.mycompany.myapp.dummy.domain;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(TEST_JAVA, "com/mycompany/myapp/dummy/infrastructure/primary/rest/DummyResourceIT.java"),
      "package com.mycompany.myapp.dummy.infrastructure.primary.rest;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(TEST_JAVA, "com/mycompany/myapp/dummy/infrastructure/secondary/DummyInMemoryRepositoryTest.java"),
      "package com.mycompany.myapp.dummy.infrastructure.secondary;"
    );
  }

  @Test
  void shouldApplyDummyGitPatchWithSpecificPackageName() {
    Project project = tmpProject();
    project.addConfig(PACKAGE_NAME, "tech.jhipster.chips");

    dummyApplicationService.applyDummyGitPatch(project);

    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, "tech/jhipster/chips/dummy/application/DummyApplicationService.java"),
      "package tech.jhipster.chips.dummy.application;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, "tech/jhipster/chips/dummy/domain/Dummy.java"),
      "package tech.jhipster.chips.dummy.domain;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, "tech/jhipster/chips/dummy/domain/DummyRepository.java"),
      "package tech.jhipster.chips.dummy.domain;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, "tech/jhipster/chips/dummy/infrastructure/primary/rest/DummyResource.java"),
      "package tech.jhipster.chips.dummy.infrastructure.primary.rest;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(MAIN_JAVA, "tech/jhipster/chips/dummy/infrastructure/secondary/DummyInMemoryRepository.java"),
      "package tech.jhipster.chips.dummy.infrastructure.secondary;"
    );

    assertFileContent(
      project,
      FileUtils.getPath(TEST_JAVA, "tech/jhipster/chips/dummy/application/DummyApplicationServiceIT.java"),
      "package tech.jhipster.chips.dummy.application;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(TEST_JAVA, "tech/jhipster/chips/dummy/domain/DummyTest.java"),
      "package tech.jhipster.chips.dummy.domain;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(TEST_JAVA, "tech/jhipster/chips/dummy/infrastructure/primary/rest/DummyResourceIT.java"),
      "package tech.jhipster.chips.dummy.infrastructure.primary.rest;"
    );
    assertFileContent(
      project,
      FileUtils.getPath(TEST_JAVA, "tech/jhipster/chips/dummy/infrastructure/secondary/DummyInMemoryRepositoryTest.java"),
      "package tech.jhipster.chips.dummy.infrastructure.secondary;"
    );
  }
}
