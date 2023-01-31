package tech.jhipster.lite.module.infrastructure.secondary;

import static tech.jhipster.lite.module.domain.JHipsterModule.LINE_BREAK;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import tech.jhipster.lite.common.domain.ExcludeFromGeneratedCodeCoverage;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.module.domain.javaproperties.PropertyKey;
import tech.jhipster.lite.module.domain.javaproperties.PropertyValue;

// todo factoriser avec le PropFilesSpringPropHandler ?
// todo later manage trailing spaces between = and ,
// todo change separator to ",\n"
public class PropertiesFileSpringFactoriesHandler {

  private final Path file;

  private static final String EQUAL = "=";

  private static final String COLLECTION_SEPARATOR = ",";

  public PropertiesFileSpringFactoriesHandler(Path file) {
    Assert.notNull("file", file);

    this.file = file;
  }

  public void set(PropertyKey key, PropertyValue value) {
    Assert.notNull("key", key);
    Assert.notNull("value", value);

    updateProperties(key, value);
  }

  @ExcludeFromGeneratedCodeCoverage
  private void updateProperties(PropertyKey key, PropertyValue value) {
    try {
      String properties = buildProperties(key, value);

      Files.writeString(file, properties);
    } catch (IOException e) {
      throw GeneratorException.technicalError("Error updating Spring Factories properties: " + e.getMessage(), e);
    }
  }

  //todo y'a des saut de ligne en trop
  // idem entre 2 options
  private String buildProperties(PropertyKey key, PropertyValue value) throws IOException {
    String currentProperties = readOrInitProperties();

    int propertyIndex = currentProperties.indexOf(propertyId(key));
    if (propertyIndex != -1) {
      StringBuilder newProperties = new StringBuilder(currentProperties);

      int eolIndex = newProperties.indexOf(LINE_BREAK, propertyIndex);
      newProperties.insert(eolIndex, "," + concatenatedValues(value));

      return newProperties.toString();
    }

    return currentProperties + propertyLine(key, value);
  }

  private String readOrInitProperties() throws IOException {
    if (Files.notExists(file)) {
      Files.createDirectories(file.getParent());
      Files.createFile(file);

      return "";
    }

    return Files.readString(file) + LINE_BREAK;
  }

  private String propertyLine(PropertyKey key, PropertyValue value) {
    return propertyId(key) + concatenatedValues(value);
  }

  private static String concatenatedValues(PropertyValue value) {
    return String.join(COLLECTION_SEPARATOR, value.get());
  }

  private String propertyId(PropertyKey key) {
    return key.get() + EQUAL;
  }
}
