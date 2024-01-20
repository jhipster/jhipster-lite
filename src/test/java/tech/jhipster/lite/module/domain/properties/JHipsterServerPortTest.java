package tech.jhipster.lite.module.domain.properties;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.shared.error.domain.NumberValueTooHighException;
import tech.jhipster.lite.shared.error.domain.NumberValueTooLowException;

@UnitTest
class JHipsterServerPortTest {

  @Test
  void shouldGetDefaultServerPortFromNullPort() {
    assertThat(new JHipsterServerPort(null).get()).isEqualTo(8080);
  }

  @Test
  void shouldGetServerPortFromPort() {
    assertThat(new JHipsterServerPort(9000).get()).isEqualTo(9000);
  }

  @ParameterizedTest
  @MethodSource("validations")
  void shouldValidatePortNumbers(Setup setup) {
    assertThatExceptionOfType(setup.exceptionType).isThrownBy(() -> new JHipsterServerPort(setup.number));
  }

  @Test
  void testToStringShowsPortNumber() {
    assertThat(new JHipsterServerPort(9000)).hasToString("9000");
  }

  static List<JHipsterServerPortTest.Setup> validations() {
    return List.of(new Setup(90000, NumberValueTooHighException.class), new Setup(-90000, NumberValueTooLowException.class));
  }

  record Setup(int number, Class<? extends Throwable> exceptionType) {}
}
