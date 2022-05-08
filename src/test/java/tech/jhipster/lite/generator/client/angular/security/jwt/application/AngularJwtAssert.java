package tech.jhipster.lite.generator.client.angular.security.jwt.application;

import static tech.jhipster.lite.TestUtils.assertFileExist;
import static tech.jhipster.lite.common.domain.FileUtils.getPath;

import tech.jhipster.lite.generator.client.angular.security.jwt.domain.AngularJwt;
import tech.jhipster.lite.generator.project.domain.Project;

public class AngularJwtAssert {

  private AngularJwtAssert() {}

  public static void assertAppJwt(Project project) {
    String rootPath = "src/main/webapp/";
    AngularJwt.angularJwtFiles().forEach((file, path) -> assertFileExist(project, getPath(rootPath + path, file)));
  }
}
