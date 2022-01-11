package tech.jhipster.lite.generator.buildtool.maven.domain;

import static tech.jhipster.lite.common.domain.WordUtils.LF;

import java.util.List;
import java.util.stream.Collectors;
import tech.jhipster.lite.common.domain.WordUtils;
import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;
import tech.jhipster.lite.generator.buildtool.generic.domain.Parent;
import tech.jhipster.lite.generator.buildtool.generic.domain.Plugin;
import tech.jhipster.lite.generator.buildtool.generic.domain.Repository;

public class Maven {

  public static final String NEEDLE_PARENT = "<!-- jhipster-needle-maven-parent -->";
  public static final String NEEDLE_DEPENDENCY = "<!-- jhipster-needle-maven-add-dependency -->";
  public static final String NEEDLE_DEPENDENCY_MANAGEMENT = "<!-- jhipster-needle-maven-add-dependency-management -->";
  public static final String NEEDLE_DEPENDENCY_TEST = "<!-- jhipster-needle-maven-add-dependency-test -->";
  public static final String NEEDLE_PLUGIN = "<!-- jhipster-needle-maven-add-plugin -->";
  public static final String NEEDLE_PROPERTIES = "<!-- jhipster-needle-maven-property -->";
  public static final String NEEDLE_PLUGIN_MANAGEMENT = "<!-- jhipster-needle-maven-add-plugin-management -->";
  public static final String NEEDLE_REPOSITORY = "<!-- jhipster-needle-maven-repository -->";

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

  public static final String REPOSITORY_BEGIN = "<repository>";
  public static final String REPOSITORY_END = "</repository>";

  public static final String ID_BEGIN = "<id>";
  public static final String ID_END = "</id>";

  public static final String URL_BEGIN = "<url>";
  public static final String URL_END = "</url>";

  public static final String NAME_BEGIN = "<name>";
  public static final String NAME_END = "</name>";

  private Maven() {}

  public static String getParent(Parent parent) {
    return getParent(parent, WordUtils.DEFAULT_INDENTATION);
  }

  public static String getParentHeader(Parent parent) {
    return getParentHeader(parent, WordUtils.DEFAULT_INDENTATION);
  }

  public static String getParentHeader(Parent parent, int indentation) {
    String begin = PARENT_BEGIN + LF;

    String content = new StringBuilder()
      .append(GROUP_ID_BEGIN)
      .append(parent.getGroupId())
      .append(GROUP_ID_END)
      .append(LF)
      .append(ARTIFACT_ID_BEGIN)
      .append(parent.getArtifactId())
      .append(ARTIFACT_ID_END)
      .append(LF)
      .toString()
      .indent(indentation);

    return begin + content;
  }

  public static String getParent(Parent parent, int indentation) {
    String header = getParentHeader(parent, indentation);

    String additionalContent = new StringBuilder()
      .append(VERSION_BEGIN)
      .append(parent.getVersion())
      .append(VERSION_END)
      .append(LF)
      .append("<relativePath />")
      .toString()
      .indent(indentation);

    return header + additionalContent + PARENT_END;
  }

  public static String getDependency(Dependency dependency, int indentation) {
    return getDependency(dependency, indentation, List.of());
  }

  public static String getDependencyHeader(Dependency dependency, int indentation) {
    String begin = DEPENDENCY_BEGIN + LF;

    String content = new StringBuilder()
      .append(GROUP_ID_BEGIN)
      .append(dependency.getGroupId())
      .append(GROUP_ID_END)
      .append(LF)
      .append(ARTIFACT_ID_BEGIN)
      .append(dependency.getArtifactId())
      .append(ARTIFACT_ID_END)
      .append(LF)
      .toString()
      .indent(indentation);

    return begin + content;
  }

  public static String getDependency(Dependency dependency, int indentation, List<Dependency> exclusions) {
    String header = getDependencyHeader(dependency, indentation);

    StringBuilder additionalBodyBuilder = new StringBuilder();

    if (dependency.isOptional()) {
      additionalBodyBuilder.append(OPTIONAL).append(LF);
    }

    dependency
      .getVersion()
      .ifPresent(version -> additionalBodyBuilder.append(VERSION_BEGIN).append(version).append(VERSION_END).append(LF));

    dependency.getScope().ifPresent(scope -> additionalBodyBuilder.append(SCOPE_BEGIN).append(scope).append(SCOPE_END).append(LF));

    dependency.getType().ifPresent(type -> additionalBodyBuilder.append(TYPE_BEGIN).append(type).append(TYPE_END).append(LF));

    if (exclusions != null && !exclusions.isEmpty()) {
      additionalBodyBuilder.append(getExclusions(exclusions, indentation)).append(LF);
    }

    String additionalBody = additionalBodyBuilder.toString().indent(indentation);

    return header + additionalBody + DEPENDENCY_END;
  }

  public static String getExclusion(Dependency exclusion, int indentation) {
    String begin = EXCLUSION_BEGIN + LF;

    String body = new StringBuilder()
      .append(GROUP_ID_BEGIN)
      .append(exclusion.getGroupId())
      .append(GROUP_ID_END)
      .append(LF)
      .append(ARTIFACT_ID_BEGIN)
      .append(exclusion.getArtifactId())
      .append(ARTIFACT_ID_END)
      .append(LF)
      .toString()
      .indent(indentation);

    return begin + body + EXCLUSION_END;
  }

  public static String getExclusions(List<Dependency> exclusions, int indentation) {
    String begin = EXCLUSIONS_BEGIN + LF;

    String body = exclusions
      .stream()
      .map(exclusion -> getExclusion(exclusion, indentation))
      .collect(Collectors.joining(LF))
      .indent(indentation);

    return begin + body + EXCLUSIONS_END;
  }

  public static String getPluginHeader(Plugin plugin, int indentation) {
    String begin = PLUGIN_BEGIN + LF;

    String content = new StringBuilder()
      .append(GROUP_ID_BEGIN)
      .append(plugin.getGroupId())
      .append(GROUP_ID_END)
      .append(LF)
      .append(ARTIFACT_ID_BEGIN)
      .append(plugin.getArtifactId())
      .append(ARTIFACT_ID_END)
      .toString()
      .indent(indentation);

    return begin + content;
  }

  public static String getPlugin(Plugin plugin, int indentation) {
    String header = getPluginHeader(plugin, indentation);

    StringBuilder additionalBodyBuilder = new StringBuilder();

    plugin.getVersion().ifPresent(version -> additionalBodyBuilder.append(VERSION_BEGIN).append(version).append(VERSION_END).append(LF));

    plugin.getAdditionalElements().ifPresent(additionalBodyBuilder::append);

    String additionalBody = additionalBodyBuilder.toString().indent(indentation);

    return header + additionalBody + PLUGIN_END;
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

  public static String getRepositoryHeader(Repository repository, int indentation) {
    String begin = REPOSITORY_BEGIN + LF;

    String content = new StringBuilder()
      .append(ID_BEGIN)
      .append(repository.getId())
      .append(ID_END)
      .append(LF)
      .append(URL_BEGIN)
      .append(repository.getUrl())
      .append(URL_END)
      .append(LF)
      .toString()
      .indent(indentation);

    return begin + content;
  }

  public static String getRepository(Repository repository, int indentation) {
    String header = getRepositoryHeader(repository, indentation);

    StringBuilder additionalBodyBuilder = new StringBuilder();

    repository.getName().ifPresent(name -> additionalBodyBuilder.append(NAME_BEGIN).append(name).append(NAME_END).append(LF));

    repository.getAdditionalElements().ifPresent(additionalBodyBuilder::append);

    String additionalBody = additionalBodyBuilder.toString().indent(indentation);

    return header + additionalBody + REPOSITORY_END;
  }
}
