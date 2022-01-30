package tech.jhipster.lite.common.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

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
