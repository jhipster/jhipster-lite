package tech.jhipster.lite.generator.project.infrastructure.secondary.executables;

import static org.junit.jupiter.api.Assertions.*;

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
      assertEquals(Npm.getExecutableCommand(), "npm.cmd");
    }
  }

  @Test
  void shouldReturnWindowsFalseForNonWindows() {
    try (MockedStatic<OSUtils> osUtils = Mockito.mockStatic(OSUtils.class)) {
      Mockito.when(OSUtils.isWindows()).thenReturn(false);
      assertEquals(Npm.getExecutableCommand(), "npm");
    }
  }
}
