package tech.jhipster.forge.generator.maven.domain;

import static tech.jhipster.forge.common.utils.FileUtils.getPath;
import static tech.jhipster.forge.common.utils.WordUtils.DEFAULT_INDENTATION;
import static tech.jhipster.forge.common.utils.WordUtils.indent;

import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.utils.FileUtils;
import tech.jhipster.forge.common.utils.WordUtils;
import tech.jhipster.forge.error.domain.Assert;

public class Maven {

  private Maven() {}

  public static boolean isMavenProject(Project project) {
    return FileUtils.exists(getPath(project.getPath(), "pom.xml"));
  }

  public static String getParent(String groupId, String artifactId, String version) {
    return getParent(groupId, artifactId, version, WordUtils.DEFAULT_INDENTATION);
  }

  public static String getParent(String groupId, String artifactId, String version, int indentation) {
    Assert.notBlank("groupId", groupId);
    Assert.notBlank("artifactId", artifactId);
    Assert.notBlank("version", version);

    StringBuilder result = new StringBuilder()
      .append(indent(1, indentation))
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
      .append("</parent>")
      .append(System.lineSeparator());

    return result.toString();
  }

  public static String getDependencies(String groupId, String artifactId) {
    return getDependencies(groupId, artifactId, false, DEFAULT_INDENTATION);
  }

  public static String getDependencies(String groupId, String artifactId, int indentation) {
    return getDependencies(groupId, artifactId, false, indentation);
  }

  public static String getDependencies(String groupId, String artifactId, boolean scopeTest) {
    return getDependencies(groupId, artifactId, scopeTest, DEFAULT_INDENTATION);
  }

  public static String getDependencies(String groupId, String artifactId, boolean scopeTest, int indentation) {
    Assert.notBlank("groupId", groupId);
    Assert.notBlank("artifactId", artifactId);

    StringBuilder result = new StringBuilder()
      .append(indent(2, indentation))
      .append("<dependency>")
      .append(System.lineSeparator())
      .append(indent(3, indentation))
      .append("<groupId>")
      .append(groupId)
      .append("</groupId>")
      .append(System.lineSeparator())
      .append(indent(3, indentation))
      .append("<artifactId>")
      .append(artifactId)
      .append("</artifactId>")
      .append(System.lineSeparator());

    if (scopeTest) {
      result.append(indent(3, indentation)).append("<scope>test</scope>").append(System.lineSeparator());
    }

    result.append(indent(2, indentation)).append("</dependency>").append(System.lineSeparator());

    return result.toString();
  }
}
