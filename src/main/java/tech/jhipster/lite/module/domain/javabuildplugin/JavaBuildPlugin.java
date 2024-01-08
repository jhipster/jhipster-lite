package tech.jhipster.lite.module.domain.javabuildplugin;

import static java.util.function.Predicate.not;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tech.jhipster.lite.module.domain.javabuild.ArtifactId;
import tech.jhipster.lite.module.domain.javabuild.GroupId;
import tech.jhipster.lite.module.domain.javabuild.VersionSlug;
import tech.jhipster.lite.module.domain.javadependency.DependencyId;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public class JavaBuildPlugin {

  private final DependencyId dependencyId;
  private final Optional<VersionSlug> versionSlug;
  private final Optional<JavaBuildPluginConfiguration> configuration;
  private final Optional<JavaBuildPluginExecutions> executions;

  private JavaBuildPlugin(JavaBuildPluginBuilder builder) {
    dependencyId = DependencyId.of(builder.groupId, builder.artifactId);
    versionSlug = Optional.ofNullable(builder.versionSlug);
    configuration = Optional.ofNullable(builder.configuration);
    executions = Optional.ofNullable(builder.executions).filter(not(Collection::isEmpty)).map(JavaBuildPluginExecutions::new);
  }

  public static JavaBuildPluginGroupIdBuilder builder() {
    return new JavaBuildPluginBuilder();
  }

  public Optional<VersionSlug> versionSlug() {
    return versionSlug;
  }

  public Optional<JavaBuildPluginConfiguration> configuration() {
    return configuration;
  }

  public Optional<JavaBuildPluginExecutions> executions() {
    return executions;
  }

  public DependencyId dependencyId() {
    return dependencyId;
  }

  public static class JavaBuildPluginBuilder
    implements JavaBuildPluginGroupIdBuilder, JavaBuildPluginArtifactIdBuilder, JavaBuildPluginOptionalBuilder {

    private GroupId groupId;
    private ArtifactId artifactId;
    private VersionSlug versionSlug;
    private JavaBuildPluginConfiguration configuration;
    private final List<JavaBuildPluginExecution> executions = new ArrayList<>();

    private JavaBuildPluginBuilder() {}

    @Override
    public JavaBuildPluginArtifactIdBuilder groupId(GroupId groupId) {
      this.groupId = groupId;

      return this;
    }

    @Override
    public JavaBuildPluginOptionalBuilder artifactId(ArtifactId artifactId) {
      this.artifactId = artifactId;

      return this;
    }

    @Override
    public JavaBuildPluginOptionalBuilder versionSlug(VersionSlug versionSlug) {
      this.versionSlug = versionSlug;

      return this;
    }

    @Override
    public JavaBuildPluginOptionalBuilder configuration(JavaBuildPluginConfiguration configuration) {
      this.configuration = configuration;
      return this;
    }

    @Override
    public JavaBuildPluginOptionalBuilder addExecution(JavaBuildPluginExecution executions) {
      this.executions.add(executions);
      return this;
    }

    @Override
    public JavaBuildPlugin build() {
      return new JavaBuildPlugin(this);
    }
  }

  public interface JavaBuildPluginGroupIdBuilder {
    JavaBuildPluginArtifactIdBuilder groupId(GroupId groupId);

    default JavaBuildPluginArtifactIdBuilder groupId(String groupId) {
      return groupId(new GroupId(groupId));
    }
  }

  public interface JavaBuildPluginArtifactIdBuilder {
    JavaBuildPluginOptionalBuilder artifactId(ArtifactId artifactId);

    default JavaBuildPluginOptionalBuilder artifactId(String artifactId) {
      return artifactId(new ArtifactId(artifactId));
    }
  }

  public interface JavaBuildPluginOptionalBuilder {
    JavaBuildPluginOptionalBuilder versionSlug(VersionSlug slug);

    JavaBuildPlugin build();

    default JavaBuildPluginOptionalBuilder versionSlug(String slug) {
      return versionSlug(new VersionSlug(slug));
    }

    JavaBuildPluginOptionalBuilder configuration(JavaBuildPluginConfiguration configuration);

    default JavaBuildPluginOptionalBuilder configuration(String configuration) {
      return configuration(new JavaBuildPluginConfiguration(configuration));
    }

    JavaBuildPluginOptionalBuilder addExecution(JavaBuildPluginExecution executions);

    default JavaBuildPluginOptionalBuilder addExecution(JavaBuildPluginExecution.JavaBuildPluginExecutionOptionalBuilder builder) {
      return addExecution(builder.build());
    }
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("dependencyId", dependencyId)
      .append("versionSlug", versionSlug.map(VersionSlug::toString).orElse("(empty)"))
      .append("configuration", configuration.map(JavaBuildPluginConfiguration::toString).orElse("(empty)"))
      .append("executions", executions.map(JavaBuildPluginExecutions::toString).orElse("(empty)"));
    return builder.toString();
  }
}
