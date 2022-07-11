package tech.jhipster.lite.module.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class LooseProjectFolderTest {

  private static final String ENDING_BY_UUID_REGEX = ".*\\w{8}(-\\w{4}){3}-\\w{12}$";

  private final LooseProjectFolder looseProjectFolder = new LooseProjectFolder();

  @Test
  void shouldBeValid() {
    assertThat(looseProjectFolder.isValid("/tmp/jhipster/project")).isTrue();
  }

  @Test
  void shouldGetValidPath() {
    assertThat(looseProjectFolder.generatePath())
      .startsWith(PathUtil.appendFileSeparatorIfNeeded(SystemUtils.JAVA_IO_TMPDIR))
      .matches(ENDING_BY_UUID_REGEX);
  }
}
