package tech.jhipster.lite.module.domain.gradleplugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradlePlugin;
import tech.jhipster.lite.module.domain.javabuild.command.AddGradlePlugin.AddGradlePluginOptionalBuilder;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommand;
import tech.jhipster.lite.module.domain.javabuild.command.JavaBuildCommands;
import tech.jhipster.lite.module.domain.javabuildprofile.BuildProfileId;
import tech.jhipster.lite.module.domain.javadependency.JavaDependenciesVersions;
import tech.jhipster.lite.shared.error.domain.Assert;

public final class JHipsterModuleGradlePlugin {

  private final Collection<GradlePlugin> plugins;

  private JHipsterModuleGradlePlugin(JHipsterModuleGradlePluginBuilder<?> builder) {
    Assert.notNull("plugins", builder.plugins);
    plugins = builder.plugins;
  }

  public static <T> JHipsterModuleGradlePluginBuilder<T> builder(T parentModuleBuilder) {
    return new JHipsterModuleGradlePluginBuilder<>(parentModuleBuilder);
  }

  public JavaBuildCommands buildChanges(JavaDependenciesVersions versions) {
    Assert.notNull("versions", versions);

    return new JavaBuildCommands(plugins.stream().map(toCommands(versions, Optional.empty())).toList());
  }

  public JavaBuildCommands buildChanges(JavaDependenciesVersions versions, BuildProfileId buildProfile) {
    Assert.notNull("versions", versions);
    Assert.notNull("buildProfile", buildProfile);

    return new JavaBuildCommands(plugins.stream().map(toCommands(versions, Optional.of(buildProfile))).toList());
  }

  private Function<GradlePlugin, JavaBuildCommand> toCommands(JavaDependenciesVersions versions, Optional<BuildProfileId> buildProfile) {
    return plugin ->
      switch (plugin) {
        case GradleCorePlugin corePlugin -> mapCorePlugin(corePlugin, versions);
        case GradleCommunityPlugin gradleCommunityPlugin -> mapCommunityPlugin(gradleCommunityPlugin, versions);
        case GradleProfilePlugin gradleProfilePlugin -> mapProfilePlugin(gradleProfilePlugin, versions, buildProfile);
      };
  }

  private JavaBuildCommand mapCorePlugin(GradleCorePlugin plugin, JavaDependenciesVersions versions) {
    AddGradlePluginOptionalBuilder commandBuilder = AddGradlePlugin.builder().plugin(plugin);
    plugin.toolVersionSlug().map(versions::get).ifPresent(commandBuilder::toolVersion);
    return commandBuilder.build();
  }

  private JavaBuildCommand mapCommunityPlugin(GradleCommunityPlugin plugin, JavaDependenciesVersions versions) {
    AddGradlePluginOptionalBuilder commandBuilder = AddGradlePlugin.builder().plugin(plugin);
    plugin.versionSlug().map(versions::get).ifPresent(commandBuilder::pluginVersion);
    return commandBuilder.build();
  }

  private JavaBuildCommand mapProfilePlugin(
    GradleProfilePlugin gradleProfilePlugin,
    JavaDependenciesVersions versions,
    Optional<BuildProfileId> buildProfile
  ) {
    AddGradlePluginOptionalBuilder commandBuilder = AddGradlePlugin.builder().plugin(gradleProfilePlugin);
    buildProfile.ifPresent(commandBuilder::buildProfile);
    gradleProfilePlugin.versionSlug().map(versions::get).ifPresent(commandBuilder::pluginVersion);
    return commandBuilder.build();
  }

  public static final class JHipsterModuleGradlePluginBuilder<T> {

    private final T parentModuleBuilder;
    private final Collection<GradlePlugin> plugins = new ArrayList<>();

    private JHipsterModuleGradlePluginBuilder(T parentModuleBuilder) {
      Assert.notNull("parentModuleBuilder", parentModuleBuilder);

      this.parentModuleBuilder = parentModuleBuilder;
    }

    public JHipsterModuleGradlePluginBuilder<T> plugin(GradlePlugin plugin) {
      Assert.notNull("plugin", plugin);

      plugins.add(plugin);

      return this;
    }

    public T and() {
      return parentModuleBuilder;
    }

    public JHipsterModuleGradlePlugin build() {
      return new JHipsterModuleGradlePlugin(this);
    }
  }
}
