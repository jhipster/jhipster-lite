package tech.jhipster.forge.common.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;
import tech.jhipster.forge.error.domain.MissingMandatoryValueException;
import tech.jhipster.forge.error.domain.UnauthorizedValueException;

@UnitTest
class CheckConfigTest {

  @Test
  void shouldValidBaseName() {
    assertThatCode(() -> CheckConfig.validBaseName("jhipster")).doesNotThrowAnyException();
    assertThatCode(() -> CheckConfig.validBaseName("JHipster")).doesNotThrowAnyException();
    assertThatCode(() -> CheckConfig.validBaseName("jhipster_project")).doesNotThrowAnyException();
    assertThatCode(() -> CheckConfig.validBaseName("JHipster1664")).doesNotThrowAnyException();
    assertThatCode(() -> CheckConfig.validBaseName("1664")).doesNotThrowAnyException();
  }

  @Test
  void shouldNotValidBaseNameWithSpace() {
    assertThatThrownBy(() -> CheckConfig.validBaseName("jhipster app")).isExactlyInstanceOf(UnauthorizedValueException.class);
  }

  @Test
  void shouldNotValidBaseNameWithSpecialCharacter() {
    assertThatThrownBy(() -> CheckConfig.validBaseName("jhipster$")).isExactlyInstanceOf(UnauthorizedValueException.class);
  }

  @Test
  void shouldNotValidBaseNameWithNull() {
    assertThatThrownBy(() -> CheckConfig.validBaseName(null)).isExactlyInstanceOf(MissingMandatoryValueException.class);
  }

  @Test
  void shouldNotValidBaseNameWithBlank() {
    assertThatThrownBy(() -> CheckConfig.validBaseName(" ")).isExactlyInstanceOf(MissingMandatoryValueException.class);
  }
}
