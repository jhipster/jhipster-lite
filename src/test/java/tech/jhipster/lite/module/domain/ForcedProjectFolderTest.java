package tech.jhipster.lite.module.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class ForcedProjectFolderTest {

  private static final String ENDING_BY_UUID_REGEX = ".*\\w{8}(-\\w{4}){3}-\\w{12}$";

  private final ForcedProjectFolder forcedProjectFolder = new ForcedProjectFolder("/tmp/jhipster");

  @Test
  void shouldNotBeValidWithNotMatchingPrefix() {
    assertThat(forcedProjectFolder.isValid("/other/folder")).isFalse();
  }

  @Test
  void shouldNotBeValidWithDotDot() {
    assertThat(forcedProjectFolder.isValid("/tmp/jhipster/../project")).isFalse();
  }

  @Test
  void shouldBeValid() {
    assertThat(forcedProjectFolder.isValid("/tmp/jhipster/project")).isTrue();
  }

  @Test
  void shouldGetValidPath() {
    assertThat(forcedProjectFolder.generatePath()).startsWith("/tmp/jhipster" + File.separator).matches(ENDING_BY_UUID_REGEX);
  }
}
