package tech.jhipster.lite.module.domain.javabuildplugin;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import tech.jhipster.lite.shared.collection.domain.JHipsterCollections;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public final class JavaBuildPluginExecution {

  private Collection<JavaBuildPluginExecutionGoal> goals;
  private Optional<JavaBuildPluginExecutionId> id;
  private Optional<JavaBuildPhase> phase;
  private Optional<JavaBuildPluginConfiguration> configuration;

  private JavaBuildPluginExecution(JavaBuildPluginExecutionBuilder builder) {
    Assert.notEmpty("goals", builder.goals);
    this.goals = JHipsterCollections.immutable(builder.goals);
    this.id = Optional.ofNullable(builder.id);
    this.phase = Optional.ofNullable(builder.phase);
    this.configuration = Optional.ofNullable(builder.configuration);
  }

  public static JavaBuildPluginExecutionGoalsBuilder builder() {
    return new JavaBuildPluginExecutionBuilder();
  }

  public Collection<JavaBuildPluginExecutionGoal> goals() {
    return goals;
  }

  public Optional<JavaBuildPluginExecutionId> id() {
    return id;
  }

  public Optional<JavaBuildPhase> phase() {
    return phase;
  }

  public Optional<JavaBuildPluginConfiguration> configuration() {
    return configuration;
  }

  public static class JavaBuildPluginExecutionBuilder
    implements JavaBuildPluginExecutionGoalsBuilder, JavaBuildPluginExecutionOptionalBuilder {

    private JavaBuildPluginExecutionId id;
    private JavaBuildPhase phase;
    private List<JavaBuildPluginExecutionGoal> goals;
    private JavaBuildPluginConfiguration configuration;

    @Override
    public JavaBuildPluginExecutionOptionalBuilder goals(JavaBuildPluginExecutionGoal... goals) {
      this.goals = List.of(goals);
      return this;
    }

    @Override
    public JavaBuildPluginExecutionOptionalBuilder phase(JavaBuildPhase phase) {
      this.phase = phase;
      return this;
    }

    @Override
    public JavaBuildPluginExecutionOptionalBuilder id(JavaBuildPluginExecutionId executionId) {
      this.id = executionId;
      return this;
    }

    @Override
    public JavaBuildPluginExecutionOptionalBuilder configuration(JavaBuildPluginConfiguration configuration) {
      this.configuration = configuration;
      return this;
    }

    @Override
    public JavaBuildPluginExecution build() {
      return new JavaBuildPluginExecution(this);
    }
  }

  public interface JavaBuildPluginExecutionGoalsBuilder {
    JavaBuildPluginExecutionOptionalBuilder goals(JavaBuildPluginExecutionGoal... goals);

    default JavaBuildPluginExecutionOptionalBuilder goals(String... goals) {
      return goals(Stream.of(goals).map(JavaBuildPluginExecutionGoal::new).toArray(JavaBuildPluginExecutionGoal[]::new));
    }
  }

  public interface JavaBuildPluginExecutionOptionalBuilder {
    JavaBuildPluginExecutionOptionalBuilder phase(JavaBuildPhase phase);

    JavaBuildPluginExecutionOptionalBuilder id(JavaBuildPluginExecutionId phase);

    default JavaBuildPluginExecutionOptionalBuilder id(String executionId) {
      return id(new JavaBuildPluginExecutionId(executionId));
    }

    JavaBuildPluginExecutionOptionalBuilder configuration(JavaBuildPluginConfiguration configuration);

    default JavaBuildPluginExecutionOptionalBuilder configuration(String configuration) {
      return configuration(new JavaBuildPluginConfiguration(configuration));
    }

    JavaBuildPluginExecution build();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
      .append("goals", goals)
      .append("id", id.map(JavaBuildPluginExecutionId::toString).orElse("(empty)"))
      .append("phase", phase.map(JavaBuildPhase::toString).orElse("(empty)"))
      .append("configuration", configuration.map(JavaBuildPluginConfiguration::toString).orElse("(empty)"));
    return builder.toString();
  }
}
