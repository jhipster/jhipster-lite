package tech.jhipster.lite.generator.project.infrastructure.secondary.executables;

import static org.junit.jupiter.api.Assertions.*;
import static tech.jhipster.lite.common.domain.OSUtils.isWindows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.common.domain.OSUtils;

@UnitTest
public class NpmTest {

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
