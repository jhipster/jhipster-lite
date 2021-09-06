package tech.jhipster.forge.common.secondary;

import static tech.jhipster.forge.TestUtils.assertFileExist;
import static tech.jhipster.forge.common.domain.FileUtils.getPath;

import java.io.File;
import org.eclipse.jgit.api.Git;
import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.common.domain.FileUtils;

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
}
