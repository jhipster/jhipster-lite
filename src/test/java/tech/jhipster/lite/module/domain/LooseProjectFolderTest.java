package tech.jhipster.lite.module.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class LooseProjectFolderTest {

  private final LooseProjectFolder looseProjectFolder = new LooseProjectFolder();

  @Test
  void shouldBeValid() {
    assertThat(looseProjectFolder.isValid("/tmp/jhipster/project")).isTrue();
  }

  @Test
  void shouldGetValidPath() {
    assertThat(looseProjectFolder.generatePath()).matches("^" + SystemUtils.JAVA_IO_TMPDIR + File.separator + ".{36}$");
  }
}
