package tech.jhipster.light.generator.buildtool.generic.domain;

import java.util.Optional;
import tech.jhipster.light.error.domain.Assert;

public class Plugin {

  private final String groupId;
  private final String artifactId;
  private final Optional<String> version;

  private Plugin(PluginBuilder builder) {
    Assert.notBlank("groupId", builder.groupId);
    Assert.notBlank("artifactId", builder.artifactId);

    this.groupId = builder.groupId;
    this.artifactId = builder.artifactId;
    this.version = optionalNotBlank(builder.version);
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

  public static class PluginBuilder {

    private String groupId;
    private String artifactId;
    private String version;

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

    public Plugin build() {
      return new Plugin(this);
    }
  }
}
