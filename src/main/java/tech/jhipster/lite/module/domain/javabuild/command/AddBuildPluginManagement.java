package tech.jhipster.lite.module.domain.javabuild.command;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.Optional;
import org.apache.commons.lang3.builder.ToStringBuilder;
import tech.jhipster.lite.module.domain.javabuildplugin.JavaBuildPlugin;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyVersion;
import tech.jhipster.lite.shared.error.domain.Assert;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

public final class AddBuildPluginManagement implements JavaBuildCommand, AddJavaBuildPlugin {

  private final JavaBuildPlugin plugin;
  private final Optional<JavaDependencyVersion> pluginVersion;

  private AddBuildPluginManagement(AddBuildPluginManagementBuilder builder) {
    Assert.notNull("javaBuildPlugin", builder.plugin);
    this.plugin = builder.plugin;
    this.pluginVersion = Optional.ofNullable(builder.pluginVersion);
  }

  @Override
  public JavaBuildPlugin plugin() {
    return plugin;
  }

  @Override
  public Optional<JavaDependencyVersion> pluginVersion() {
    return pluginVersion;
  }

  public static AddBuildPluginManagementPluginBuilder builder() {
    return new AddBuildPluginManagementBuilder();
  }

  private static class AddBuildPluginManagementBuilder
    implements AddBuildPluginManagementPluginBuilder, AddBuildPluginManagementOptionalBuilder {

    private JavaBuildPlugin plugin;
    private JavaDependencyVersion pluginVersion;

    private AddBuildPluginManagementBuilder() {}

    @Override
    public AddBuildPluginManagementBuilder plugin(JavaBuildPlugin javaBuildPlugin) {
      this.plugin = javaBuildPlugin;
      return this;
    }

    @Override
    public AddBuildPluginManagement pluginVersion(JavaDependencyVersion pluginVersion) {
      this.pluginVersion = pluginVersion;
      return build();
    }

    public AddBuildPluginManagement build() {
      return new AddBuildPluginManagement(this);
    }
  }

  public interface AddBuildPluginManagementPluginBuilder {
    AddBuildPluginManagementOptionalBuilder plugin(JavaBuildPlugin javaBuildPlugin);
  }

  public interface AddBuildPluginManagementOptionalBuilder {
    AddBuildPluginManagement pluginVersion(JavaDependencyVersion version);
    AddBuildPluginManagement build();
  }

  @Override
  @ExcludeFromGeneratedCodeCoverage
  public String toString() {
    ToStringBuilder builder = new ToStringBuilder(this, SHORT_PREFIX_STYLE)
      .append("plugin", plugin)
      .append("pluginVersion", pluginVersion.map(JavaDependencyVersion::toString).orElse("(empty)"));
    return builder.toString();
  }
}
