package tech.jhipster.lite.common.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;

@UnitTest
class JsonUtilsTest {

  @Test
  void shouldReturnNewObjectMapperWhenItIsNotInitialized() {
    assertThat(JsonUtils.getObjectMapper()).isNotNull();
  }

  @Test
  void shouldReturnSingletonObjectMapper() {
    ObjectMapper objectMapper = JsonUtils.getObjectMapper();
    assertThat(JsonUtils.getObjectMapper()).isEqualTo(objectMapper);
  }
}
