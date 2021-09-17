package tech.jhipster.forge.generator.maven.domain;

import static tech.jhipster.forge.common.utils.WordUtils.indent;

import tech.jhipster.forge.common.utils.WordUtils;
import tech.jhipster.forge.error.domain.Assert;

public class Plugin {

  public static String NEEDLE = "<!-- jhipster-needle-maven-add-plugin -->";

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

  public String get() {
    return get(WordUtils.DEFAULT_INDENTATION);
  }

  public String get(int indentation) {
    StringBuilder result = new StringBuilder()
      .append("<plugin>")
      .append(System.lineSeparator())
      .append(indent(4, indentation))
      .append("<groupId>")
      .append(groupId)
      .append("</groupId>")
      .append(System.lineSeparator())
      .append(indent(4, indentation))
      .append("<artifactId>")
      .append(artifactId)
      .append("</artifactId>")
      .append(System.lineSeparator())
      .append(indent(3, indentation))
      .append("</plugin>")
      .append(System.lineSeparator());

    return result.toString();
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
