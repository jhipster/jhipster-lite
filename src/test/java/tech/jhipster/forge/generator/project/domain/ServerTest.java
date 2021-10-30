package tech.jhipster.forge.generator.project.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;

@UnitTest
class ServerTest {

  @ParameterizedTest
  @EnumSource(ServerFramework.class)
  void shouldBuildServerWithFramework(ServerFramework framework) {
    Server server = new Server(framework);

    assertThat(server.framework()).isEqualTo(framework);
  }

  @Test
  void shouldNotBuildServerWithNullFramework() {
    assertThatThrownBy(() -> new Server(null)).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("framework");
  }
}
