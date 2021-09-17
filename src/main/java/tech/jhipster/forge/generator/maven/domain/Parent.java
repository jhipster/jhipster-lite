package tech.jhipster.forge.generator.maven.domain;

import static tech.jhipster.forge.common.utils.WordUtils.indent;

import tech.jhipster.forge.common.utils.WordUtils;
import tech.jhipster.forge.error.domain.Assert;

public class Parent {

  public static String NEEDLE = "<!-- jhipster-needle-maven-parent -->";

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

  public String get() {
    return get(WordUtils.DEFAULT_INDENTATION);
  }

  public String get(int indentation) {
    StringBuilder result = new StringBuilder()
      .append("<parent>")
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append("<groupId>")
      .append(groupId)
      .append("</groupId>")
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append("<artifactId>")
      .append(artifactId)
      .append("</artifactId>")
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append("<version>")
      .append(version)
      .append("</version>")
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append("<relativePath/>")
      .append(System.lineSeparator())
      .append(indent(1, indentation))
      .append("</parent>");

    return result.toString();
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
