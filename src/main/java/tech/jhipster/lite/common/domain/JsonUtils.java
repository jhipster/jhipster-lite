package tech.jhipster.lite.common.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

  public static final String CARRIAGE_RETURN = System.lineSeparator();

  private static ObjectMapper objectMapper = null;

  private JsonUtils() {
    // Cannot be instantiated
  }

  public static ObjectMapper getObjectMapper() {
    if (objectMapper != null) {
      return objectMapper;
    }
    objectMapper = new ObjectMapper();
    return objectMapper;
  }
}
