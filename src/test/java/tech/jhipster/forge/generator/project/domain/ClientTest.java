package tech.jhipster.forge.generator.project.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;

@UnitTest
class ClientTest {

  @ParameterizedTest
  @EnumSource(ClientFramework.class)
  void shouldBuildClientWithFramework(ClientFramework framework) {
    Client client = new Client(framework);

    assertThat(client.framework()).isEqualTo(framework);
  }

  @Test
  void shouldNotBuildClientWithNullFramework() {
    assertThatThrownBy(() -> new Client(null)).isExactlyInstanceOf(MissingMandatoryValueException.class).hasMessageContaining("framework");
  }
}
