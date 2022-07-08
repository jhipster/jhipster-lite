package tech.jhipster.lite.module.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class ForcedProjectFolderTest {

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
    assertThat(forcedProjectFolder.generatePath()).matches("^/tmp/jhipster" + File.separator + ".{36}$");
  }
}
