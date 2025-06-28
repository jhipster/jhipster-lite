package tech.jhipster.lite.module.domain.nodejs;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class NodePackageManagerTest {

  @Test
  void shouldComputeWindowsCommand() {
    assertThat(NodePackageManager.NPM.windowsCommand()).isEqualTo("npm.cmd");
  }
}
