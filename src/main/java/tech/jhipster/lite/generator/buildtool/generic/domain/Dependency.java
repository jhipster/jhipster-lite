package tech.jhipster.lite.generator.buildtool.generic.domain;

import java.util.Optional;
import tech.jhipster.lite.error.domain.Assert;

public class Dependency {

  private final String groupId;
  private final String artifactId;
  private final boolean optional;
  private final String version;
  private final String scope;
  private final String type;

  private Dependency(Dependency.DependencyBuilder builder) {
    Assert.notBlank("groupId", builder.groupId);
    Assert.notBlank("artifactId", builder.artifactId);

    this.groupId = builder.groupId;
    this.artifactId = builder.artifactId;
    this.optional = builder.optional;
    this.version = notBlank(builder.version);
    this.scope = notBlank(builder.scope);
    this.type = notBlank(builder.type);
  }

  private String notBlank(String value) {
    if (value != null && value.isBlank()) {
      return null;
    }
    return value;
  }

  public static Dependency.DependencyBuilder builder() {
    return new Dependency.DependencyBuilder();
  }

  public String getGroupId() {
    return groupId;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public boolean isOptional() {
    return optional;
  }

  public Optional<String> getVersion() {
    return Optional.ofNullable(version);
  }

  public Optional<String> getScope() {
    return Optional.ofNullable(scope);
  }

  public Optional<String> getType() {
    return Optional.ofNullable(type);
  }

  public static class DependencyBuilder {

    private String groupId;
    private String artifactId;
    private boolean optional;
    private String version;
    private String scope;
    private String type;

    public Dependency.DependencyBuilder groupId(String groupId) {
      this.groupId = groupId;
      return this;
    }

    public Dependency.DependencyBuilder artifactId(String artifactId) {
      this.artifactId = artifactId;
      return this;
    }

    public Dependency.DependencyBuilder optional() {
      this.optional = true;
      return this;
    }

    public Dependency.DependencyBuilder version(String version) {
      this.version = version;
      return this;
    }

    public DependencyBuilder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public DependencyBuilder type(String type) {
      this.type = type;
      return this;
    }

    public Dependency build() {
      return new Dependency(this);
    }
  }
}
