package tech.jhipster.lite.module.domain.git;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.gitignore.GitIgnore;
import tech.jhipster.lite.module.domain.gitignore.GitIgnoreEntry.GitIgnoreComment;
import tech.jhipster.lite.module.domain.gitignore.GitIgnoreEntry.GitIgnorePattern;

@UnitTest
class GitIgnoreTest {

  @Test
  void toSimpleString() {
    GitIgnore gitIgnore = new GitIgnore(List.of(new GitIgnoreComment("A comment"), new GitIgnorePattern("target/")));

    assertThat(gitIgnore).hasToString("[# A comment, target/]");
  }
}
