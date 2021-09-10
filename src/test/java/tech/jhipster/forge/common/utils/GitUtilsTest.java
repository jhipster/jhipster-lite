package tech.jhipster.forge.common.utils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.jhipster.forge.TestUtils.assertFileExist;
import static tech.jhipster.forge.common.domain.Constants.TEST_TEMPLATE_RESOURCES;
import static tech.jhipster.forge.common.utils.FileUtils.getPath;

import java.io.File;
import java.io.FileNotFoundException;
import org.eclipse.jgit.api.Git;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.Constants;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.common.utils.GitUtils;

@UnitTest
class GitUtilsTest {

  @Test
  void shouldInitAddAndCommit() throws Exception {
    String dir = FileUtils.tmpDirForTest();

    Git git = GitUtils.init(dir);
    File file = File.createTempFile("hello", ".world", new File(dir));

    GitUtils.add(git, dir);
    GitUtils.commit(git, dir, "1st commit");

    assertFileExist(getPath(dir, ".git"));
    assertFileExist(getPath(dir, file.getName()));
  }

  @Test
  void shouldApply() throws Exception {
    String dir = FileUtils.tmpDirForTest();
    Git git = GitUtils.init(dir);

    GitUtils.apply(git, getPath(TEST_TEMPLATE_RESOURCES, "utils", "example.patch"));

    assertFileExist(getPath(dir, "example.md"));
  }

  @Test
  void shouldNotApplyWhenNoExistingPatch() throws Exception {
    String dir = FileUtils.tmpDirForTest();
    Git git = GitUtils.init(dir);

    assertThatThrownBy(() -> GitUtils.apply(git, getPath(TEST_TEMPLATE_RESOURCES, "utils", "unknown.patch")))
      .isExactlyInstanceOf(FileNotFoundException.class);
  }
}
