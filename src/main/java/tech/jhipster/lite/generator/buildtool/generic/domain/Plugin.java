package tech.jhipster.lite.generator.buildtool.generic.domain;

import java.util.Optional;
import tech.jhipster.lite.error.domain.Assert;

public class Plugin {

  private final String groupId;
  private final String artifactId;
  private final Optional<String> version;
  private final Optional<String> additionalElements;

  private Plugin(PluginBuilder builder) {
    Assert.notBlank("groupId", builder.groupId);
    Assert.notBlank("artifactId", builder.artifactId);

    this.groupId = builder.groupId;
    this.artifactId = builder.artifactId;
    this.version = optionalNotBlank(builder.version);
    this.additionalElements = optionalNotBlank(builder.additionalElements);
  }

  private Optional<String> optionalNotBlank(String value) {
    if (value == null || value.isBlank()) {
      return Optional.empty();
    }
    return Optional.of(value);
  }

  public static PluginBuilder builder() {
    return new PluginBuilder();
  }

  public String getGroupId() {
    return groupId;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public Optional<String> getVersion() {
    return version;
  }

  public Optional<String> getAdditionalElements() {
    return additionalElements;
  }

  public static class PluginBuilder {

    private String groupId;
    private String artifactId;
    private String version;
    private String additionalElements;

    public PluginBuilder groupId(String groupId) {
      this.groupId = groupId;
      return this;
    }

    public PluginBuilder artifactId(String artifactId) {
      this.artifactId = artifactId;
      return this;
    }

    public PluginBuilder version(String version) {
      this.version = version;
      return this;
    }

    public PluginBuilder additionalElements(String additionalElements) {
      this.additionalElements = additionalElements;
      return this;
    }

    public Plugin build() {
      return new Plugin(this);
    }
  }
}
