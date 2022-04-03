package tech.jhipster.lite.common.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import tech.jhipster.lite.error.domain.GeneratorException;

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

  public static String addValueInArray(String fieldName, String value, String jsonContent) {
    ObjectNode rootNode;
    ObjectMapper objectMapper = getObjectMapper();
    try {
      rootNode = objectMapper.readValue(jsonContent, ObjectNode.class);
    } catch (JsonProcessingException e) {
      throw new GeneratorException("Cannot read json content : " + e.getMessage());
    }

    ArrayNode arrayNode;
    if (rootNode.has(fieldName)) {
      if (rootNode.get(fieldName).getNodeType() == JsonNodeType.ARRAY) {
        arrayNode = ((ArrayNode) rootNode.get(fieldName));
      } else {
        throw new GeneratorException("Cannot add value in field: is not an array");
      }
    } else {
      arrayNode = objectMapper.createArrayNode();
      rootNode.set(fieldName, arrayNode);
    }
    arrayNode.add(value);

    try {
      return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
    } catch (JsonProcessingException e) {
      throw new GeneratorException("Cannot serialize node : " + e.getMessage());
    }
  }
}
