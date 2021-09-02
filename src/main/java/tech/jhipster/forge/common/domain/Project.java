package tech.jhipster.forge.common.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import tech.jhipster.forge.error.domain.Assert;

public class Project {

  private final String path;
  private final Map<String, String> config;

  private Project(ProjectBuilder builder) {
    Assert.notBlank("path", builder.path);
    Assert.notNull("config", builder.config);

    this.path = builder.path;
    this.config = builder.config;
  }

  public static ProjectBuilder builder() {
    return new ProjectBuilder();
  }

  public String getPath() {
    return path;
  }

  public Map<String, String> getConfig() {
    return config;
  }

  public Optional<String> getConfig(String key) {
    return Optional.ofNullable(config.get(key));
  }

  public void addConfig(String key, String value) {
    config.putIfAbsent(key, value);
  }

  public static class ProjectBuilder {

    private String path;
    private Map<String, String> config = new HashMap<>();

    public Project build() {
      return new Project(this);
    }

    public ProjectBuilder path(String path) {
      this.path = path;
      return this;
    }

    public ProjectBuilder config(Map<String, String> config) {
      if (config != null) {
        this.config = config;
      }
      return this;
    }
  }
}
