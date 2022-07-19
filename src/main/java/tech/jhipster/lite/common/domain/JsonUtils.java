package tech.jhipster.lite.common.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {

  private JsonUtils() {
    // Cannot be instantiated
  }

  public static ObjectMapper getObjectMapper() {
    return JsonUtilsHolder.objectMapper;
  }

  private static class JsonUtilsHolder {

    private static ObjectMapper objectMapper = new ObjectMapper().configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
  }
}
