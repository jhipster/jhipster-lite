package tech.jhipster.lite.generator.client.angular.admin.health.application;

import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;

import tech.jhipster.lite.generator.client.angular.admin.health.domain.AngularHealth;
import tech.jhipster.lite.generator.project.domain.Project;

public class AngularHealthAssert {

  private AngularHealthAssert() {}

  public static void assertAppHealth(Project project) {
    String rootPath = "src/main/webapp/";
    AngularHealth.angularHealthFiles().forEach((file, path) -> assertFileExist(project, getPath(rootPath + path, file)));
  }
}
