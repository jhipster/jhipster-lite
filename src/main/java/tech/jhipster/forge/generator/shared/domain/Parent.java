package tech.jhipster.forge.generator.shared.domain;

import tech.jhipster.forge.error.domain.Assert;

public class Parent {

  private final String groupId;
  private final String artifactId;
  private final String version;

  private Parent(ParentBuilder builder) {
    Assert.notBlank("groupId", builder.groupId);
    Assert.notBlank("artifactId", builder.artifactId);
    Assert.notBlank("version", builder.version);

    this.groupId = builder.groupId;
    this.artifactId = builder.artifactId;
    this.version = builder.version;
  }

  public static ParentBuilder builder() {
    return new ParentBuilder();
  }

  public String getGroupId() {
    return groupId;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public String getVersion() {
    return version;
  }

  public static class ParentBuilder {

    private String groupId;
    private String artifactId;
    private String version;

    public ParentBuilder groupId(String groupId) {
      this.groupId = groupId;
      return this;
    }

    public ParentBuilder artifactId(String artifactId) {
      this.artifactId = artifactId;
      return this;
    }

    public ParentBuilder version(String version) {
      this.version = version;
      return this;
    }

    public Parent build() {
      return new Parent(this);
    }
  }
}
