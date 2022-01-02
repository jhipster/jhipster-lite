package tech.jhipster.lite.generator.packagemanager.npm.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static tech.jhipster.lite.TestUtils.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.CommandRepository;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class NpmApplicationServiceIT {

  @Autowired
  NpmApplicationService npmApplicationService;

  @SpyBean
  CommandRepository commandRepository;

  @Test
  void shouldNpmInstall() {
    Project project = tmpProjectWithPackageJson();
    npmApplicationService.install(project);

    assertFileExist(project, "node_modules");
  }

  @Test
  void shouldPrettify() {
    Project project = tmpProjectWithPackageJson();
    npmApplicationService.prettify(project);

    verify(commandRepository).npmPrettierFormat(any(Project.class));
  }
}
