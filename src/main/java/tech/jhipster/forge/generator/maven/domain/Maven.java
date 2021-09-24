package tech.jhipster.forge.generator.maven.domain;

import static tech.jhipster.forge.common.utils.WordUtils.indent;

import tech.jhipster.forge.common.utils.WordUtils;
import tech.jhipster.forge.generator.shared.domain.Dependency;
import tech.jhipster.forge.generator.shared.domain.Parent;
import tech.jhipster.forge.generator.shared.domain.Plugin;

public class Maven {

  public static String NEEDLE_PARENT = "<!-- jhipster-needle-maven-parent -->";
  public static String NEEDLE_DEPENDENCY = "<!-- jhipster-needle-maven-add-dependency -->";
  public static String NEEDLE_DEPENDENCY_TEST = "<!-- jhipster-needle-maven-add-dependency-test -->";
  public static String NEEDLE_PLUGIN = "<!-- jhipster-needle-maven-add-plugin -->";

  private Maven() {}

  public static String getParent(Parent parent) {
    return getParent(parent, WordUtils.DEFAULT_INDENTATION);
  }

  public static String getParent(Parent parent, int indentation) {
    StringBuilder result = new StringBuilder()
      .append("<parent>")
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append("<groupId>")
      .append(parent.getGroupId())
      .append("</groupId>")
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append("<artifactId>")
      .append(parent.getArtifactId())
      .append("</artifactId>")
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append("<version>")
      .append(parent.getVersion())
      .append("</version>")
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append("<relativePath/>")
      .append(System.lineSeparator())
      .append(indent(1, indentation))
      .append("</parent>");

    return result.toString();
  }

  public static String getDependency(Dependency dependency) {
    return getDependency(dependency, WordUtils.DEFAULT_INDENTATION);
  }

  public static String getDependency(Dependency dependency, int indentation) {
    StringBuilder result = new StringBuilder()
      .append("<dependency>")
      .append(System.lineSeparator())
      .append(indent(3, indentation))
      .append("<groupId>")
      .append(dependency.getGroupId())
      .append("</groupId>")
      .append(System.lineSeparator())
      .append(indent(3, indentation))
      .append("<artifactId>")
      .append(dependency.getArtifactId())
      .append("</artifactId>")
      .append(System.lineSeparator());

    dependency
      .getVersion()
      .ifPresent(version ->
        result.append(indent(3, indentation)).append("<version>").append(version).append("</version>").append(System.lineSeparator())
      );

    dependency
      .getScope()
      .ifPresent(scope ->
        result.append(indent(3, indentation)).append("<scope>").append(scope).append("</scope>").append(System.lineSeparator())
      );

    result.append(indent(2, indentation)).append("</dependency>");

    return result.toString();
  }

  public static String getPlugin(Plugin plugin) {
    return getPlugin(plugin, WordUtils.DEFAULT_INDENTATION);
  }

  public static String getPlugin(Plugin plugin, int indentation) {
    StringBuilder result = new StringBuilder()
      .append("<plugin>")
      .append(System.lineSeparator())
      .append(indent(4, indentation))
      .append("<groupId>")
      .append(plugin.getGroupId())
      .append("</groupId>")
      .append(System.lineSeparator())
      .append(indent(4, indentation))
      .append("<artifactId>")
      .append(plugin.getArtifactId())
      .append("</artifactId>")
      .append(System.lineSeparator())
      .append(indent(3, indentation))
      .append("</plugin>");

    return result.toString();
  }
}
