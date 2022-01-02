package tech.jhipster.lite.generator.project.infrastructure.secondary.executables;

import static org.junit.jupiter.api.Assertions.*;
import static tech.jhipster.lite.common.domain.OSUtils.isWindows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import tech.jhipster.lite.UnitTest;

@UnitTest
public class NpmTest {

  @Test
  @EnabledOnOs(OS.WINDOWS)
  void shouldReturnWindowsTrueForWindows() {
    assertEquals(Npm.getExecutableCommand(), "npm.cmd");
  }

  @Test
  @DisabledOnOs(OS.WINDOWS)
  void shouldReturnWindowsFalseForNonWindows() {
    assertEquals(Npm.getExecutableCommand(), "npm");
  }
}
