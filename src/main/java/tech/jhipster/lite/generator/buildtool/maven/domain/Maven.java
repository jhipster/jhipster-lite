package tech.jhipster.lite.generator.buildtool.maven.domain;

import static tech.jhipster.lite.common.domain.WordUtils.indent;

import java.util.List;
import java.util.stream.Collectors;
import tech.jhipster.lite.common.domain.WordUtils;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.buildtool.generic.domain.Parent;
import tech.jhipster.lite.generator.buildtool.generic.domain.Plugin;

public class Maven {

  public static final String NEEDLE_PARENT = "<!-- jhipster-needle-maven-parent -->";
  public static final String NEEDLE_DEPENDENCY = "<!-- jhipster-needle-maven-add-dependency -->";
  public static final String NEEDLE_DEPENDENCY_MANAGEMENT = "<!-- jhipster-needle-maven-add-dependency-management -->";
  public static final String NEEDLE_DEPENDENCY_TEST = "<!-- jhipster-needle-maven-add-dependency-test -->";
  public static final String NEEDLE_PLUGIN = "<!-- jhipster-needle-maven-add-plugin -->";
  public static final String NEEDLE_PROPERTIES = "<!-- jhipster-needle-maven-property -->";
  public static final String NEEDLE_PLUGIN_MANAGEMENT = "<!-- jhipster-needle-maven-add-plugin-management -->";

  public static final String PARENT_BEGIN = "<parent>";
  public static final String PARENT_END = "</parent>";

  public static final String DEPENDENCY_BEGIN = "<dependency>";
  public static final String DEPENDENCY_END = "</dependency>";

  public static final String GROUP_ID_BEGIN = "<groupId>";
  public static final String GROUP_ID_END = "</groupId>";

  public static final String PLUGIN_BEGIN = "<plugin>";
  public static final String PLUGIN_END = "</plugin>";

  public static final String PLUGIN_MANAGEMENT_BEGIN = "<pluginManagement>";
  public static final String PLUGIN_MANAGEMENT_END = "</pluginManagement>";

  public static final String ARTIFACT_ID_BEGIN = "<artifactId>";
  public static final String ARTIFACT_ID_END = "</artifactId>";

  public static final String OPTIONAL = "<optional>true</optional>";

  public static final String VERSION_BEGIN = "<version>";
  public static final String VERSION_END = "</version>";

  public static final String SCOPE_BEGIN = "<scope>";
  public static final String SCOPE_END = "</scope>";

  public static final String TYPE_BEGIN = "<type>";
  public static final String TYPE_END = "</type>";

  public static final String EXCLUSIONS_BEGIN = "<exclusions>";
  public static final String EXCLUSIONS_END = "</exclusions>";

  public static final String EXCLUSION_BEGIN = "<exclusion>";
  public static final String EXCLUSION_END = "</exclusion>";

  private Maven() {}

  public static String getParent(Parent parent) {
    return getParent(parent, WordUtils.DEFAULT_INDENTATION);
  }

  public static String getParentHeader(Parent parent) {
    return getParentHeader(parent, WordUtils.DEFAULT_INDENTATION);
  }

  public static String getParentHeader(Parent parent, int indentation) {
    StringBuilder result = new StringBuilder()
      .append(PARENT_BEGIN)
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append(GROUP_ID_BEGIN)
      .append(parent.getGroupId())
      .append(GROUP_ID_END)
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append(ARTIFACT_ID_BEGIN)
      .append(parent.getArtifactId())
      .append(ARTIFACT_ID_END)
      .append(System.lineSeparator());
    return result.toString();
  }

  public static String getParent(Parent parent, int indentation) {
    StringBuilder result = new StringBuilder()
      .append(getParentHeader(parent, indentation))
      .append(indent(2, indentation))
      .append(VERSION_BEGIN)
      .append(parent.getVersion())
      .append(VERSION_END)
      .append(System.lineSeparator())
      .append(indent(2, indentation))
      .append("<relativePath />")
      .append(System.lineSeparator())
      .append(indent(1, indentation))
      .append(PARENT_END);

    return result.toString();
  }

  public static String getDependency(Dependency dependency, int indentation) {
    return getDependency(dependency, indentation, List.of());
  }

  public static String getDependencyHeader(Dependency dependency, int indentation) {
    String begin = DEPENDENCY_BEGIN + "\n";

    String content = new StringBuilder()
      .append(GROUP_ID_BEGIN)
      .append(dependency.getGroupId())
      .append(GROUP_ID_END)
      .append("\n")
      .append(ARTIFACT_ID_BEGIN)
      .append(dependency.getArtifactId())
      .append(ARTIFACT_ID_END)
      .append("\n")
      .toString()
      .indent(indentation);

    return begin + content;
  }

  public static String getDependency(Dependency dependency, int indentation, List<Dependency> exclusions) {
    String header = getDependencyHeader(dependency, indentation);

    StringBuilder additionalBodyBuilder = new StringBuilder();

    if (dependency.isOptional()) {
      additionalBodyBuilder.append(OPTIONAL).append("\n");
    }

    dependency
      .getVersion()
      .ifPresent(version -> additionalBodyBuilder.append(VERSION_BEGIN).append(version).append(VERSION_END).append("\n"));

    dependency.getScope().ifPresent(scope -> additionalBodyBuilder.append(SCOPE_BEGIN).append(scope).append(SCOPE_END).append("\n"));

    dependency.getType().ifPresent(type -> additionalBodyBuilder.append(TYPE_BEGIN).append(type).append(TYPE_END).append("\n"));

    if (exclusions != null && !exclusions.isEmpty()) {
      additionalBodyBuilder.append(getExclusions(exclusions, indentation)).append("\n");
    }

    String additionalBody = additionalBodyBuilder.toString().indent(indentation);

    return header + additionalBody + DEPENDENCY_END;
  }

  public static String getExclusion(Dependency exclusion, int indentation) {
    String begin = EXCLUSION_BEGIN + "\n";

    String body = new StringBuilder()
      .append(GROUP_ID_BEGIN)
      .append(exclusion.getGroupId())
      .append(GROUP_ID_END)
      .append("\n")
      .append(ARTIFACT_ID_BEGIN)
      .append(exclusion.getArtifactId())
      .append(ARTIFACT_ID_END)
      .append("\n")
      .toString()
      .indent(indentation);

    return begin + body + EXCLUSION_END;
  }

  public static String getExclusions(List<Dependency> exclusions, int indentation) {
    String begin = EXCLUSIONS_BEGIN + "\n";

    String body = exclusions
      .stream()
      .map(exclusion -> getExclusion(exclusion, indentation))
      .collect(Collectors.joining("\n"))
      .indent(indentation);

    return begin + body + EXCLUSIONS_END;
  }

  public static String getPlugin(Plugin plugin) {
    return getPlugin(plugin, WordUtils.DEFAULT_INDENTATION, 3);
  }

  public static String getPlugin(Plugin plugin, int indentation) {
    return getPlugin(plugin, indentation, 3);
  }

  public static String getPluginHeader(Plugin plugin) {
    return getPluginHeader(plugin, WordUtils.DEFAULT_INDENTATION, 3);
  }

  public static String getPluginHeader(Plugin plugin, int indentation) {
    return getPluginHeader(plugin, indentation, 3);
  }

  public static String getPluginManagement(Plugin plugin) {
    return getPlugin(plugin, WordUtils.DEFAULT_INDENTATION, 4);
  }

  public static String getPluginManagement(Plugin plugin, int indentation) {
    return getPlugin(plugin, indentation, 4);
  }

  public static String getPluginManagementHeader(Plugin plugin) {
    return getPluginHeader(plugin, WordUtils.DEFAULT_INDENTATION, 4);
  }

  public static String getPluginManagementHeader(Plugin plugin, int indentation) {
    return getPluginHeader(plugin, indentation, 4);
  }

  public static String getPluginHeader(Plugin plugin, int indentation, int initialIndentation) {
    StringBuilder result = new StringBuilder()
      .append(PLUGIN_BEGIN)
      .append(System.lineSeparator())
      .append(indent(initialIndentation + 1, indentation))
      .append(GROUP_ID_BEGIN)
      .append(plugin.getGroupId())
      .append(GROUP_ID_END)
      .append(System.lineSeparator())
      .append(indent(initialIndentation + 1, indentation))
      .append(ARTIFACT_ID_BEGIN)
      .append(plugin.getArtifactId())
      .append(ARTIFACT_ID_END)
      .append(System.lineSeparator());
    return result.toString();
  }

  public static String getPlugin(Plugin plugin, int indentation, int initialIndentation) {
    StringBuilder result = new StringBuilder().append(getPluginHeader(plugin, indentation, initialIndentation));

    plugin
      .getVersion()
      .ifPresent(version ->
        result
          .append(indent(initialIndentation + 1, indentation))
          .append(VERSION_BEGIN)
          .append(version)
          .append(VERSION_END)
          .append(System.lineSeparator())
      );

    //replace '\n' to make the multi-line additionalConfiguration platform specific
    plugin
      .getAdditionalElements()
      .ifPresent(additionalElements ->
        result.append(
          plugin.getAdditionalElements().get().indent((initialIndentation + 1) * indentation).replace("\n", System.lineSeparator())
        )
      );

    result.append(indent(initialIndentation, indentation)).append(PLUGIN_END);

    return result.toString();
  }

  public static String getProperty(String key, String version) {
    return new StringBuilder()
      .append("<")
      .append(key)
      .append(".version>")
      .append(version)
      .append("</")
      .append(key)
      .append(".version>")
      .toString();
  }
}
