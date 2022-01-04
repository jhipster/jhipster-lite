package tech.jhipster.lite.common.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import tech.jhipster.lite.UnitTest;

@UnitTest
class Base64UtilsTest {

  @Test
  void shouldReturnBase64StringWithoutValue() {
    try (MockedStatic<Base64Utils> utilities = Mockito.mockStatic(Base64Utils.class, InvocationOnMock::callRealMethod)) {
      assertNotNull(Base64Utils.getBase64Secret());
      utilities.verify(() -> Base64Utils.getRandomHexString(64), times(1));
    }
  }

  @Test
  void shouldReturnBase64StringWithValue() {
    try (MockedStatic<Base64Utils> utilities = Mockito.mockStatic(Base64Utils.class, InvocationOnMock::callRealMethod)) {
      assertNotNull(Base64Utils.getBase64Secret("test-value"));
      utilities.verify(() -> Base64Utils.getRandomHexString(Mockito.anyInt()), never());
    }
  }
}
