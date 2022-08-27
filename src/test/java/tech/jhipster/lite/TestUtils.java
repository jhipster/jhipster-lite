package tech.jhipster.lite;

import tech.jhipster.lite.generator.project.domain.Project;

public class TestUtils {

  public static Project tmpProject() {
    return tmpProjectBuilder().build();
  }

  private static Project.ProjectBuilder tmpProjectBuilder() {
    return Project.builder().folder(TestFileUtils.tmpDirForTest());
  }
}
