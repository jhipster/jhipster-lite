package tech.jhipster.lite.generator.packagemanager.npm.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.OSUtils;

@UnitTest
class NpmTest {

  @Test
  void shouldReturnWindowsTrueForWindows() {
    try (MockedStatic<OSUtils> osUtils = Mockito.mockStatic(OSUtils.class)) {
      Mockito.when(OSUtils.isWindows()).thenReturn(true);
      assertThat(Npm.getExecutableCommand()).isEqualTo("npm.cmd");
    }
  }

  @Test
  void shouldReturnWindowsFalseForNonWindows() {
    try (MockedStatic<OSUtils> osUtils = Mockito.mockStatic(OSUtils.class)) {
      Mockito.when(OSUtils.isWindows()).thenReturn(false);
      assertThat(Npm.getExecutableCommand()).isEqualTo("npm");
    }
  }
}
