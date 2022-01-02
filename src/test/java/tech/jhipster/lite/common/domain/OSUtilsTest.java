package tech.jhipster.lite.common.domain;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.lite.common.domain.OSUtils.isWindows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import tech.jhipster.lite.UnitTest;

@UnitTest
class OSUtilsTest {

  @Test
  @EnabledOnOs(OS.WINDOWS)
  void shouldReturnWindowsTrueForWindows() {
    assertTrue(isWindows());
  }

  @Test
  @DisabledOnOs(OS.WINDOWS)
  void shouldReturnWindowsFalseForNonWindows() {
    assertFalse(isWindows());
  }
}
