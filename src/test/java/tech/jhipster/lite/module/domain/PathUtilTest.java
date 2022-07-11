package tech.jhipster.lite.module.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class PathUtilTest {

  @Test
  void shouldAppendFileSeparator() {
    assertThat(PathUtil.appendFileSeparatorIfNeeded("path")).isEqualTo("path" + File.separator);
  }

  @Test
  void shouldNotAppendFileSeparatorWhenExisting() {
    assertThat(PathUtil.appendFileSeparatorIfNeeded("path" + File.separator)).isEqualTo("path" + File.separator);
  }
}
