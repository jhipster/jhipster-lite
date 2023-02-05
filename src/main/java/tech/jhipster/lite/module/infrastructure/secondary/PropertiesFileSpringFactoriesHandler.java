package tech.jhipster.lite.module.infrastructure.secondary;

import static tech.jhipster.lite.module.domain.JHipsterModule.LINE_BREAK;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.module.domain.javaproperties.PropertyKey;
import tech.jhipster.lite.module.domain.javaproperties.PropertyValue;

public class PropertiesFileSpringFactoriesHandler {

  private final Path file;

  private static final String EQUAL = "=";

  private static final String COLLECTION_SEPARATOR = ",\\" + System.lineSeparator() + "  ";

  public PropertiesFileSpringFactoriesHandler(Path file) {
    Assert.notNull("file", file);

    this.file = file;
  }

  public void set(PropertyKey key, PropertyValue value) {
    Assert.notNull("key", key);
    Assert.notNull("value", value);

    updateProperties(key, value);
  }

  private void updateProperties(PropertyKey key, PropertyValue value) {
    try {
      String properties = buildProperties(key, value);

      Files.writeString(file, properties);
    } catch (IOException e) {
      throw GeneratorException.technicalError("Error updating Spring Factories properties: " + e.getMessage(), e);
    }
  }

  private String buildProperties(PropertyKey key, PropertyValue value) throws IOException {
    String currentProperties = readOrInitProperties();

    int propertyIndex = currentProperties.indexOf(propertyId(key));
    if (propertyIndex != -1) {
      return appendValuesToExistingPropertyKey(propertyIndex, value, currentProperties);
    }
    return addNewProperty(key, value, currentProperties);
  }

  private String addNewProperty(PropertyKey key, PropertyValue value, String currentProperties) {
    return currentProperties + propertyLine(key, value) + LINE_BREAK;
  }

  private static String appendValuesToExistingPropertyKey(int propertyIndex, PropertyValue value, String currentProperties) {
    StringBuilder newProperties = new StringBuilder(currentProperties);

    int eolIndex = newProperties.indexOf(LINE_BREAK, propertyIndex);
    newProperties.insert(eolIndex, "," + joinedPropertyValues(value));

    return newProperties.toString();
  }

  private String readOrInitProperties() throws IOException {
    if (Files.notExists(file)) {
      Files.createDirectories(file.getParent());
      Files.createFile(file);

      return "";
    }

    return Files.readString(file);
  }

  private String propertyLine(PropertyKey key, PropertyValue value) {
    return propertyId(key) + joinedPropertyValues(value);
  }

  private static String joinedPropertyValues(PropertyValue value) {
    return String.join(COLLECTION_SEPARATOR, value.get());
  }

  private String propertyId(PropertyKey key) {
    return key.get() + EQUAL;
  }
}
