package tech.jhipster.lite.common.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.error.domain.GeneratorException;

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

  @Nested
  class AddValueInArrayTest {

    @Test
    void shouldAddValueInExistingArray() {
      String jsonContent =
        """
        {
          "include": ["value1"],
          "exclude": ["value1",
            "value2"]
        }
        """;

      String result = JsonUtils.addValueInArray("exclude", "value3", jsonContent);

      assertThat(result.lines().toList())
        .isEqualTo(
          """
        {
          "include" : [ "value1" ],
          "exclude" : [ "value1", "value2", "value3" ]
        }
        """.lines()
            .toList()
        );
    }

    @Test
    void shouldAddValueInNewArray() {
      String jsonContent = """
        {
          "include": ["value1"]
        }
        """;

      String result = JsonUtils.addValueInArray("exclude", "value1", jsonContent);

      assertThat(result.lines().toList())
        .isEqualTo("""
        {
          "include" : [ "value1" ],
          "exclude" : [ "value1" ]
        }
        """.lines().toList());
    }

    @Test
    void shouldNotAddWhenJsonCannotBeRead() {
      String jsonContent = "{invalid_json}}";

      assertThatThrownBy(() -> JsonUtils.addValueInArray("field", "value", jsonContent)).isInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotAddWhenFieldInNotArray() {
      String jsonContent = """
        {
          "field": "not_array"
        }
        """;

      assertThatThrownBy(() -> JsonUtils.addValueInArray("field", "value", jsonContent)).isInstanceOf(GeneratorException.class);
    }

    @Test
    void shouldNotAddWhenSerializationFailed() throws JsonProcessingException {
      String jsonContent = """
        {
          "include": ["value1"]
        }
        """;

      JsonUtils.getObjectMapper();
      ObjectMapper spiedObjectMapper = spy(JsonUtils.getObjectMapper());
      ObjectWriter spiedObjectWriter = spy(spiedObjectMapper.writer());
      when(spiedObjectMapper.writerWithDefaultPrettyPrinter()).thenReturn(spiedObjectWriter);
      when(spiedObjectWriter.writeValueAsString(any())).thenThrow(JsonProcessingException.class);

      try (MockedStatic<JsonUtils> jsonUtils = Mockito.mockStatic(JsonUtils.class)) {
        jsonUtils.when(JsonUtils::getObjectMapper).thenReturn(spiedObjectMapper);
        jsonUtils.when(() -> JsonUtils.addValueInArray(anyString(), anyString(), anyString())).thenCallRealMethod();
        assertThatThrownBy(() -> JsonUtils.addValueInArray("field", "value", jsonContent)).isInstanceOf(GeneratorException.class);
      }
    }
  }
}
