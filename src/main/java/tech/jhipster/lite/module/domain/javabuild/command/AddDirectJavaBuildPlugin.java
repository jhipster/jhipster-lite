package tech.jhipster.lite.module.domain.javabuild.command;

import java.util.Optional;
import tech.jhipster.lite.module.domain.javabuildplugin.JavaBuildPlugin;
import tech.jhipster.lite.module.domain.javadependency.JavaDependencyVersion;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class AddDirectJavaBuildPlugin implements JavaBuildCommand, AddJavaBuildPlugin {

  private final JavaBuildPlugin javaBuildPlugin;
  private final Optional<JavaDependencyVersion> pluginVersion;

  private AddDirectJavaBuildPlugin(AddDirectJavaBuildPluginBuilder builder) {
    Assert.notNull("javaBuildPlugin", builder.javaBuildPlugin);
    this.javaBuildPlugin = builder.javaBuildPlugin;
    this.pluginVersion = Optional.ofNullable(builder.pluginVersion);
  }

  @Override
  public JavaBuildPlugin plugin() {
    return javaBuildPlugin;
  }

  @Override
  public Optional<JavaDependencyVersion> pluginVersion() {
    return pluginVersion;
  }

  public static AddDirectJavaBuildPluginPluginBuilder builder() {
    return new AddDirectJavaBuildPluginBuilder();
  }

  private static class AddDirectJavaBuildPluginBuilder
    implements AddDirectJavaBuildPluginPluginBuilder, AddDirectJavaBuildPluginOptionalBuilder {

    private JavaBuildPlugin javaBuildPlugin;
    private JavaDependencyVersion pluginVersion;

    private AddDirectJavaBuildPluginBuilder() {}

    @Override
    public AddDirectJavaBuildPluginBuilder javaBuildPlugin(JavaBuildPlugin javaBuildPlugin) {
      this.javaBuildPlugin = javaBuildPlugin;
      return this;
    }

    @Override
    public AddDirectJavaBuildPlugin pluginVersion(JavaDependencyVersion pluginVersion) {
      this.pluginVersion = pluginVersion;
      return build();
    }

    public AddDirectJavaBuildPlugin build() {
      return new AddDirectJavaBuildPlugin(this);
    }
  }

  public interface AddDirectJavaBuildPluginPluginBuilder {
    AddDirectJavaBuildPluginOptionalBuilder javaBuildPlugin(JavaBuildPlugin javaBuildPlugin);
  }

  public interface AddDirectJavaBuildPluginOptionalBuilder {
    AddDirectJavaBuildPlugin pluginVersion(JavaDependencyVersion version);
    AddDirectJavaBuildPlugin build();
  }
}
