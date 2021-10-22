package tech.jhipster.forge.common.domain;

import static tech.jhipster.forge.common.domain.DefaultConfig.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import tech.jhipster.forge.error.domain.Assert;
import tech.jhipster.forge.error.domain.UnauthorizedValueException;

public class Project {

  private final String path;
  private final Map<String, Object> config;

  private Project(ProjectBuilder builder) {
    Assert.notBlank("path", builder.path);
    Assert.notNull("config", builder.config);

    this.path = builder.path;
    this.config = builder.config;

    validateConfig();
  }

  public static ProjectBuilder builder() {
    return new ProjectBuilder();
  }

  public String getPath() {
    return path;
  }

  public Map<String, Object> getConfig() {
    return config;
  }

  public Optional<Object> getConfig(String key) {
    return Optional.ofNullable(config.get(key));
  }

  public void addConfig(String key, Object value) {
    config.putIfAbsent(key, value);
    validateConfig();
  }

  public void addDefaultConfig(String key) {
    DefaultConfig.get(key).ifPresent(value -> addConfig(key, value));
  }

  public Optional<String> getBaseName() {
    return getStringConfig(BASE_NAME);
  }

  public Optional<String> getPackageName() {
    return getStringConfig(PACKAGE_NAME);
  }

  public Optional<String> getPackageNamePath() {
    return getPackageName().map(packageName -> packageName.replaceAll("\\.", File.separator));
  }

  public Optional<String> getStringConfig(String key) {
    Object value = config.get(key);
    if (value == null) {
      return Optional.empty();
    } else if (value instanceof String) {
      return Optional.of((String) value);
    }
    throw new UnauthorizedValueException("The " + key + " is not valid");
  }

  public Optional<Integer> getIntegerConfig(String key) {
    Object value = config.get(key);
    if (value == null) {
      return Optional.empty();
    } else if (value instanceof Integer) {
      return Optional.of((Integer) value);
    }
    throw new UnauthorizedValueException("The " + key + " is not valid");
  }

  private void validateConfig() {
    getBaseName().ifPresent(CheckConfig::validBaseName);
    getPackageName().ifPresent(CheckConfig::validPackageName);
  }

  public static class ProjectBuilder {

    private String path;
    private Map<String, Object> config = new HashMap<>();

    public Project build() {
      return new Project(this);
    }

    public ProjectBuilder path(String path) {
      this.path = path;
      return this;
    }

    public ProjectBuilder config(Map<String, Object> config) {
      if (config != null) {
        this.config = config;
      }
      return this;
    }
  }
}
