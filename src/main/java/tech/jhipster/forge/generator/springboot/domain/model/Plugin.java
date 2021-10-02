package tech.jhipster.forge.generator.springboot.domain.model;

import tech.jhipster.forge.error.domain.Assert;

public class Plugin {

  private final String groupId;
  private final String artifactId;

  private Plugin(PluginBuilder builder) {
    Assert.notBlank("groupId", builder.groupId);
    Assert.notBlank("artifactId", builder.artifactId);

    this.groupId = builder.groupId;
    this.artifactId = builder.artifactId;
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

  public static class PluginBuilder {

    private String groupId;
    private String artifactId;

    public PluginBuilder groupId(String groupId) {
      this.groupId = groupId;
      return this;
    }

    public PluginBuilder artifactId(String artifactId) {
      this.artifactId = artifactId;
      return this;
    }

    public Plugin build() {
      return new Plugin(this);
    }
  }
}
