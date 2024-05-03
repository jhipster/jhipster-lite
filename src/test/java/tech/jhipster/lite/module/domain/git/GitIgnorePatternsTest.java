package tech.jhipster.lite.module.domain.git;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.git.GitIgnoreEntry.GitIgnoreComment;
import tech.jhipster.lite.module.domain.git.GitIgnoreEntry.GitIgnorePattern;

@UnitTest
class GitIgnorePatternsTest {

  @Test
  void toSimpleString() {
    GitIgnorePatterns gitIgnorePatterns = new GitIgnorePatterns(
      List.of(new GitIgnoreComment("A comment"), new GitIgnorePattern("target/"))
    );

    assertThat(gitIgnorePatterns).hasToString("[# A comment, target/]");
  }
}
