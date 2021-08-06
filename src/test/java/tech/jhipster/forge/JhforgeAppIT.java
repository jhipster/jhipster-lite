package tech.jhipster.forge;

import static org.assertj.core.api.Assertions.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

@IntegrationTest
class JhforgeAppIT {

  @Test
  void shouldMain() {
    assertThatCode(() -> JhforgeApp.main(new String[] {})).doesNotThrowAnyException();
  }

  @Test
  void shouldMainWithServerSslKeyStore() {
    System.setProperty("server.ssl.key-store", "https://localhost");
    assertThatCode(() -> JhforgeApp.main(new String[] {})).doesNotThrowAnyException();
  }

  @Test
  void shouldMainWithContextPath() {
    System.setProperty("server.servlet.context-path", "/chips");
    assertThatCode(() -> JhforgeApp.main(new String[] {})).doesNotThrowAnyException();
  }

  @Test
  void shouldMainWithoutHostAddress() {
    try (MockedStatic<InetAddress> inetAddress = Mockito.mockStatic(InetAddress.class)) {
      inetAddress.when(InetAddress::getLocalHost).thenThrow(new UnknownHostException());

      assertThatCode(() -> JhforgeApp.main(new String[] {})).doesNotThrowAnyException();
    }
  }
}
