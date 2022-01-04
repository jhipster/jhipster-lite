package tech.jhipster.lite;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

@IntegrationTest
class JHLiteAppIT {

  @Test
  void shouldMain() {
    assertThatCode(() -> JHLiteApp.main(new String[] {})).doesNotThrowAnyException();
  }

  @Test
  void shouldApplicationRunning() {
    String result = JHLiteApp.applicationRunning("jhlite");
    assertThat(result).isEqualTo("  Application 'jhlite' is running!\n");
  }

  @Test
  void shouldAccessUrlLocalWithoutServerPort() {
    String result = JHLiteApp.accessUrlLocal(null, null, null);
    assertThat(result).isEmpty();
  }

  @Test
  void shouldAccessUrlLocalWithoutContextPath() {
    String result = JHLiteApp.accessUrlLocal("http", "8080", "/");
    assertThat(result).isEqualTo("  Local: \thttp://localhost:8080/swagger-ui.html\n");
  }

  @Test
  void shouldAccessUrlLocalWithContextPath() {
    String result = JHLiteApp.accessUrlLocal("http", "8080", "/lite/");
    assertThat(result).isEqualTo("  Local: \thttp://localhost:8080/lite/swagger-ui.html\n");
  }

  @Test
  void shouldAccessUrlExternalWithoutServerPort() {
    String result = JHLiteApp.accessUrlExternal(null, null, null, null);
    assertThat(result).isEmpty();
  }

  @Test
  void shouldAccessUrlExternalWithoutContextPath() {
    String result = JHLiteApp.accessUrlExternal("http", "127.0.1.1", "8080", "/");
    assertThat(result).isEqualTo("  External: \thttp://127.0.1.1:8080/swagger-ui.html\n");
  }

  @Test
  void shouldAccessUrlExternalWithContextPath() {
    String result = JHLiteApp.accessUrlExternal("http", "127.0.1.1", "8080", "/lite/");
    assertThat(result).isEqualTo("  External: \thttp://127.0.1.1:8080/lite/swagger-ui.html\n");
  }

  @Test
  void shouldConfigServerWithoutConfigServerStatus() {
    String result = JHLiteApp.configServer(null);
    assertThat(result).isEmpty();
  }

  @Test
  void shouldConfigServer() {
    String result = JHLiteApp.configServer("Connected to the JHipster Registry running in Docker");
    assertThat(result).contains("Config Server: Connected to the JHipster Registry running in Docker");
  }

  @Test
  void shouldGetProtocol() {
    assertThat(JHLiteApp.getProtocol(null)).isEqualTo("http");
  }

  @Test
  void shouldGetProtocolForBlank() {
    assertThat(JHLiteApp.getProtocol(" ")).isEqualTo("https");
  }

  @Test
  void shouldGetProtocolForValue() {
    assertThat(JHLiteApp.getProtocol("https")).isEqualTo("https");
  }

  @Test
  void shouldGetContextPath() {
    assertThat(JHLiteApp.getContextPath("/chips")).isEqualTo("/chips");
  }

  @Test
  void shouldGetContextPathForNull() {
    assertThat(JHLiteApp.getContextPath(null)).isEqualTo("/");
  }

  @Test
  void shouldGetContextPathForBlank() {
    assertThat(JHLiteApp.getContextPath(" ")).isEqualTo("/");
  }

  @Test
  void shouldGetHost() {
    assertThatCode(JHLiteApp::getHostAddress).doesNotThrowAnyException();
  }

  @Test
  void shouldGetHostWithoutHostAddress() {
    try (MockedStatic<InetAddress> inetAddress = Mockito.mockStatic(InetAddress.class)) {
      inetAddress.when(InetAddress::getLocalHost).thenThrow(new UnknownHostException());

      String result = JHLiteApp.getHostAddress();

      assertThat(result).isEqualTo("localhost");
    }
  }
}
